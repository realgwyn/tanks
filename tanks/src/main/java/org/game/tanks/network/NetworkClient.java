package org.game.tanks.network;

import java.io.IOException;
import java.net.InetAddress;

import org.game.tanks.network.model.NetworkDataModel;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.udp.GameSnapshot;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage.KeepAlive;
import com.esotericsoftware.kryonet.Listener;

public class NetworkClient {

  private Client client;

  private UDPListener udpListener;
  private TCPListener tcpListener;
  private ConnectionListener connectionListener;
  private ConnectionAddress serverAddress;

  public NetworkClient() {
    client = new Client();
    NetworkDataModel.register(client);
    initActions();
  }

  public InetAddress discoverLanServer(int serverUdpPort, int timeoutMillis) {
    return client.discoverHost(serverUdpPort, timeoutMillis);
  }

  public void connect(ConnectionAddress addr) throws NetworkException {
    client.start();
    try {
      if (addr.getUdpPort() <= 0) {
        client.connect(5000, addr.getAddress(), addr.getTcpPort());
      } else {
        client.connect(5000, addr.getAddress(), addr.getTcpPort(), addr.getUdpPort());
      }
      serverAddress = addr;
    } catch (IOException e) {
      e.printStackTrace();
      serverAddress = null;
      throw new NetworkException("Unable to connect to host: " + addr.getAddress() + " reason: " + e.getMessage());
    }
  }

  private void initActions() {
    client.addListener(new Listener() {
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
      public void connected(Connection conn) {
        connectionListener.connected(conn);
      }

      @Override
      public void disconnected(Connection conn) {
        connectionListener.disconnected(conn);
      }
    });
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

  public void sendTCP(TCPMessage request) {
    client.sendTCP(request);
  }

  public void sendUDP(UDPMessage request) {
    client.sendUDP(request);
  }

  public void disconnect() {
    client.stop();
    serverAddress = null;
  }

  public InetAddress discoverHost(int udpPort, int timeoutMillis) {
    return client.discoverHost(udpPort, timeoutMillis);
  }

  public static void main(String[] args) throws NetworkException, InterruptedException {
    NetworkClient client = new NetworkClient();
    client.setTCPListener(new TCPListener() {
      @Override
      public void receivedTCPMessage(Connection conn, TCPMessage response) {
        System.out.println("Received Response");
        if (response instanceof ChatMessage) {
          ChatMessage msg = (ChatMessage) response;
          System.out.println("Received ChatMessage: " + msg.getText());
        } else {
          System.out.println("Received other type of response: " + response);
        }
      }

    });

    client.setUDPListener(new UDPListener() {
      @Override
      public void receivedUDPMessage(Connection conn, UDPMessage response) {
        if (response instanceof GameSnapshot) {
          System.out.println("Received GameSnapshot:" + response);
        }
      }

    });
    client.connect(new ConnectionAddress("Game Server 1", "127.0.0.1", 55555, 55556));

    Thread.sleep(200);

    ChatMessage msg = new ChatMessage();
    msg.setText("Hello server!");
    client.sendTCP(msg);

    Thread.sleep(20000);
  }

  public Client getClient() {
    return client;
  }

}
