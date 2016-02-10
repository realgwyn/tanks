package org.game.tanks.network;

import java.io.IOException;

import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.NetworkDataModel;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.udp.GameSnapshot;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class NetworkServer {

  private int udpPort;
  private int tcpPort;
  private boolean running;

  Server server;
  UDPListener udpListener;
  TCPListener tcpListener;

  public NetworkServer() {
    server = new Server();
    NetworkDataModel.register(server);
    initActions();
  }

  public void start(int udpPort, int tcpPort) throws NetworkException {
    System.out.println("Starting server at tcpPort: " + tcpPort + ", udpPort: " + udpPort);
    this.udpPort = udpPort;
    this.tcpPort = tcpPort;
    server.start();
    try {
      server.bind(tcpPort, udpPort);

      run();
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
          udpListener.receivedRequest(conn, (UDPMessage) object);
        } else if (object instanceof TCPMessage) {
          tcpListener.receivedRequest(conn, (TCPMessage) object);
        }
      }
    });
  }

  public void run() {
    running = true;
    while (running) {
      server.sendToAllUDP(new GameSnapshot());
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void stop() {
    running = false;
    server.stop();
  }

  public void setUDPListener(UDPListener listener) {
    udpListener = listener;
  }

  public void setTCPListener(TCPListener listener) {
    tcpListener = listener;
  }

  public void sendTCPResponse(Connection conn, TCPMessage response) {
    conn.sendTCP(response);
  }

  public void sendUDPResponse(Connection conn, UDPMessage response) {
    conn.sendUDP(response);
  }

  public static void main(String[] args) throws NetworkException, InterruptedException {
    final NetworkServer server = new NetworkServer();
    server.setTCPListener(new TCPListener() {
      @Override
      public void receivedResponse(Connection conn, TCPMessage request) {
        System.out.println("Received Response");
      }

      @Override
      public void receivedRequest(Connection conn, TCPMessage request) {
        System.out.println("Received Request");
        if (request instanceof ChatMessage) {
          ChatMessage msg = (ChatMessage) request;
          System.out.println("Received receivedRequest ChatMessage: " + msg.getText());
          System.out.println("Sending response");
          ChatMessage response = new ChatMessage();
          response.setText("hello " + conn.getRemoteAddressTCP().getHostName());
          server.sendTCPResponse(conn, response);
        } else if (request instanceof Command) {
          System.out.println("received Command packet " + request.getClass().getSimpleName());
        }
      }
    });
    server.start(55555, 55556);
  }

}
