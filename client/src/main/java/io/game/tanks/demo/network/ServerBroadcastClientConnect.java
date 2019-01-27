package io.game.tanks.demo.network;

import java.net.InetAddress;

import io.game.tanks.network.ConnectionAddress;
import io.game.tanks.network.ConnectionListener;
import io.game.tanks.network.NetworkClient;
import io.game.tanks.network.NetworkException;
import io.game.tanks.network.NetworkServer;
import io.game.tanks.network.TCPListener;
import io.game.tanks.network.UDPListener;
import io.game.tanks.network.model.TCPMessage;
import io.game.tanks.network.model.UDPMessage;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

public class ServerBroadcastClientConnect {

  public static int serverUdpPort = 12345;
  public static int serverTcpPort = 12346;

  public static void main(String[] args) {

    new Thread(new Runnable() {

      NetworkServer server = new NetworkServer();

      @Override
      public void run() {
        try {

          server.start(12346, 12345);

          server.setConnectionListener(new ConnectionListener() {
            @Override
            public void disconnected(Connection c) {
              System.out.println(c);
              System.out.println("SERVER: disconnected " + c.getID());
            }

            @Override
            public void connected(Connection c) {
              System.out.println("SERVER: connected " + c.getRemoteAddressTCP());
            }
          });

          server.setTCPListener(new TCPListener() {
            @Override
            public void receivedTCPMessage(Connection conn, TCPMessage message) {
              System.out.println("SERVER: received TCP: " + message.getClass().getSimpleName());
            }
          });

          server.setUDPListener(new UDPListener() {
            @Override
            public void receivedUDPMessage(Connection conn, UDPMessage message) {
              System.out.println("SERVER: received UDP " + message.getClass().getSimpleName());
            }
          });

          while (true) {

            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();

    new Thread(new Runnable() {

      NetworkClient client = new NetworkClient();

      @Override
      public void run() {
        try {

          client.setTCPListener(new TCPListener() {
            @Override
            public void receivedTCPMessage(Connection conn, TCPMessage message) {
              System.out.println("CLIENT: received TCP: " + message.getClass().getSimpleName());
            }
          });
          client.setUDPListener(new UDPListener() {
            @Override
            public void receivedUDPMessage(Connection conn, UDPMessage message) {
              System.out.println("CLIENT: received UDP: " + message.getClass().getSimpleName());
            }
          });

          Client wrappedClient = client.getClient();
          // wrappedClient.start();
          InetAddress serverAddress = null;

          while (serverAddress == null) {
            serverAddress = wrappedClient.discoverHost(serverUdpPort, 1000);
          }

          ConnectionAddress conAddr = new ConnectionAddress(
              "Server at " + serverAddress.getHostAddress(),
              serverAddress.getHostAddress(), serverUdpPort, serverTcpPort);

          new Thread("Connct") {
            @Override
            public void run() {
              try {
                client.connect(conAddr);
              } catch (NetworkException e) {
                e.printStackTrace();
              }
            }
          }.start();

          long start = System.currentTimeMillis();
          while (true) {

            client.sendTCP(new TCPMessage());
            if (System.currentTimeMillis() - start > 10000) {
              client.disconnect();
            }

            try {
              Thread.sleep(10);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

}
