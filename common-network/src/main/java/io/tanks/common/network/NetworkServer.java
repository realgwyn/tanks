package io.tanks.common.network;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import io.tanks.common.network.model.Command;
import io.tanks.common.network.model.NetworkDataModel;
import io.tanks.common.network.model.TCPMessage;
import io.tanks.common.network.model.UDPMessage;
import io.tanks.common.network.model.message.ChatMessage;

public class NetworkServer {

  Server server;
  UDPListener udpListener;
  TCPListener tcpListener;
  ConnectionListener connectionListener;

  Queue<ConnectionInfo> incomingConnections;
  Queue<Integer> closedConnectionIDs;

  public NetworkServer() {
    incomingConnections = new ConcurrentLinkedQueue<>();
    closedConnectionIDs = new ConcurrentLinkedQueue<>();
    server = new Server();
    NetworkDataModel.register(server);
    initActions();
  }

  public void start(int tcpPort, int udpPort) throws NetworkException {
    System.out.println("Starting server at tcpPort: " + tcpPort + ", udpPort: " + udpPort);
    try {
      server.bind(tcpPort, udpPort);
      server.start();
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
          udpListener.receivedUDPMessage(conn, (UDPMessage) object);
        } else if (object instanceof TCPMessage) {
          tcpListener.receivedTCPMessage(conn, (TCPMessage) object);
        } else if (object instanceof KeepAlive) {
          // Ignore KeepAlive messages
        } else {
          System.out.println("Received unknown message " + object.getClass().getSimpleName());
        }
      }

      @Override
      public void connected(Connection c) {
        connectionListener.connected(c);
      }

      @Override
      public void disconnected(Connection c) {
        connectionListener.disconnected(c);
      }
    });
  }

  public void stop() {
    server.stop();
  }

  public void setConnectionListener(ConnectionListener connectionListener) {
    this.connectionListener = connectionListener;
  }

  public void setUDPListener(UDPListener listener) {
    udpListener = listener;
  }

  public void setTCPListener(TCPListener listener) {
    tcpListener = listener;
  }

  public void sendTCP(int connectionID, TCPMessage msg) {
    server.sendToTCP(connectionID, msg);
  }

  public void sendUDP(int connectionID, TCPMessage msg) {
    server.sendToUDP(connectionID, msg);
  }

  public void sendToAllTCP(TCPMessage msg) {
    server.sendToAllTCP(msg);
  }

  public void sendToAllUDP(UDPMessage msg) {
    server.sendToAllUDP(msg);
  }

  public void sendToAllExceptTCP(int connectionID, TCPMessage msg) {
    server.sendToAllExceptTCP(connectionID, msg);
  }

  public void sendToAllExceptUDP(int connectionID, UDPMessage msg) {
    server.sendToAllExceptUDP(connectionID, msg);
  }

  public Queue<ConnectionInfo> getIncommingConnections() {
    return incomingConnections;
  }

  public Queue<Integer> getClosedConnections() {
    return closedConnectionIDs;
  }

  public Connection getConnectionById(int id) {
    Connection[] activeConnections = server.getConnections();
    for (int i = 0; i < activeConnections.length; i++) {
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
          server.sendTCP(conn.getID(), response);
        } else if (request instanceof Command) {
          System.out.println("received Command packet " + request.getClass().getSimpleName());
        }
      }
    });
    server.start(55555, 55556);
  }

}
