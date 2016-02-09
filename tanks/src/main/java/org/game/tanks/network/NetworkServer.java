package org.game.tanks.network;

import java.io.IOException;

import org.game.tanks.network.model.NetworkDataModel;
import org.game.tanks.network.model.PacketType;
import org.game.tanks.network.model.TCPRequest;
import org.game.tanks.network.model.TCPResponse;
import org.game.tanks.network.model.UDPRequest;
import org.game.tanks.network.model.UDPResponse;

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
      public void received(Connection conn, Object object) {
        if (object instanceof UDPRequest) {
          udpListener.receivedRequest(conn, (UDPRequest) object);
        } else if (object instanceof TCPRequest) {
          tcpListener.receivedRequest(conn, (TCPRequest) object);
        }
      }
    });
  }
  
  public void run(){
    running = true;
    while(running){
      server.sendToAllUDP(new UDPResponse(PacketType.SNAPSHOT, "<id><players positions>".getBytes()));
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

  public void sendTCPResponse(Connection conn, TCPResponse response) {
    conn.sendTCP(response);
  }

  public void sendUDPResponse(Connection conn, UDPResponse response) {
    conn.sendUDP(response);
  }

  public static void main(String[] args) throws NetworkException, InterruptedException {
    final NetworkServer server = new NetworkServer();
    server.setTCPListener(new TCPListener() {
      public void receivedResponse(Connection conn, TCPResponse request) {
        System.out.println("Received Response");
      }

      public void receivedRequest(Connection conn, TCPRequest request) {
        System.out.println("Received Request");
        if (request.getType() == PacketType.MESSAGE) {
          System.out.println("Received request.data.string:" + new String(request.getBytes()));
          System.out.println("Sending response");
          String responseMsg = "hello " + conn.getRemoteAddressTCP().getHostName();
          server.sendTCPResponse(conn, new TCPResponse(PacketType.MESSAGE, responseMsg.getBytes()));
        } else if (request.getType() == PacketType.COMMAND) {
          System.out.println("received command packet doing stuffs");
        }
      }
    });
    server.start(55555, 55556);
  }



}
