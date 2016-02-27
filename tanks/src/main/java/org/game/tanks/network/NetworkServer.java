package org.game.tanks.network;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.NetworkDataModel;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.network.model.message.ChatMessage;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

// TODO: implement adding and removing connections
public class NetworkServer {

  Server server;
  UDPListener udpListener;
  TCPListener tcpListener;

  Queue<Connection> incomingConnections;
  Queue<Connection> closedConnections;

  public NetworkServer() {
    incomingConnections = new ConcurrentLinkedQueue<>();
    closedConnections = new ConcurrentLinkedQueue<>();
    server = new Server();
    NetworkDataModel.register(server);
    initActions();
  }


  public void start(int tcpPort, int udpPort) throws NetworkException {
    System.out.println("Starting server at tcpPort: " + tcpPort + ", udpPort: " + udpPort);
    server.start();
    try {
      server.bind(tcpPort, udpPort);
    } catch (IOException e) {
      e.printStackTrace();
      throw new NetworkException("Unable to start the server, reason:" + e.getMessage());
    }
  }

  private void initActions() {
    server.addListener(new Listener() {
      @Override
      public void received(Connection conn, Object object) {
        if (object instanceof UDPMessage) {
          System.out.println("<UDP(id:" + conn.getID() + ")");
          udpListener.receivedUDPMessage(conn, (UDPMessage) object);
        } else if (object instanceof TCPMessage) {
          System.out.println("<TCP(id:" + conn.getID() + ")");
          tcpListener.receivedTCPMessage(conn, (TCPMessage) object);
        }
      }
    });
  }

  public void stop() {
    server.stop();
  }

  public void setUDPListener(UDPListener listener) {
    udpListener = listener;
  }

  public void setTCPListener(TCPListener listener) {
    tcpListener = listener;
  }

  public void sendTCP(int connectionID, TCPMessage msg) {
    Connection conn = getConnectionById(connectionID);
    if (conn != null) {
      conn.sendTCP(msg);
    }
  }

  public void sendUDP(int connectionID, TCPMessage msg) {
    Connection conn = getConnectionById(connectionID);
    if (conn != null) {
      conn.sendUDP(msg);
    }
  }

  public void sendTCP(Connection conn, TCPMessage msg) {
    System.out.println(">TCP(id:" + conn.getID() + ")");
    conn.sendTCP(msg);
  }

  public void sendUDP(Connection conn, UDPMessage msg) {
    System.out.println(">UDP(id:" + conn.getID() + ")");
    conn.sendUDP(msg);
  }

  public void sendToAllTCP(TCPMessage msg) {
    System.out.println(">TCPall");
    server.sendToAllTCP(msg);
  }

  public void sendToAllUDP(UDPMessage msg) {
    System.out.println(">UDPall)");
    server.sendToAllUDP(msg);
  }

  public void sendToAllExceptTCP(int connectionID, TCPMessage msg) {
    System.out.println(">TCPxor(id:" + connectionID + ")");
    server.sendToAllExceptTCP(connectionID, msg);
  }

  public void sendToAllExceptUDP(int connectionID, UDPMessage msg) {
    System.out.println(">UDPxor(id:" + connectionID + ")");
    server.sendToAllExceptUDP(connectionID, msg);
  }

  public Queue<Connection> getIncommingConnections() {
    return incomingConnections;
  }

  public Queue<Connection> getClosedConnections() {
    return closedConnections;
  }

  public Connection getConnectionById(int id) {
    Connection [] activeConnections = server.getConnections(); 
    for(int i=0;i<activeConnections.length;i++){
      if (activeConnections[i].getID() == id) {
        return activeConnections[i];
      }
    }
    return null;
  }

  public static void main(String[] args) throws NetworkException, InterruptedException {
    final NetworkServer server = new NetworkServer();
    server.setTCPListener(new TCPListener() {

      @Override
      public void receivedTCPMessage(Connection conn, TCPMessage request) {
        System.out.println("Received Request");
        if (request instanceof ChatMessage) {
          ChatMessage msg = (ChatMessage) request;
          System.out.println("Received receivedRequest ChatMessage: " + msg.getText());
          System.out.println("Sending response");
          ChatMessage response = new ChatMessage();
          response.setText("hello " + conn.getRemoteAddressTCP().getHostName());
          server.sendTCP(conn, response);
        } else if (request instanceof Command) {
          System.out.println("received Command packet " + request.getClass().getSimpleName());
        }
      }
    });
    server.start(55555, 55556);
  }

}
