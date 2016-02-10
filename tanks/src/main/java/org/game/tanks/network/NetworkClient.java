package org.game.tanks.network;

import java.io.IOException;

import org.game.tanks.network.model.NetworkDataModel;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.udp.GameSnapshot;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkClient {

  private Client client;
  private boolean connected = false;
  private UDPListener udpListener;
  private TCPListener tcpListener;
  private ConnectionAddress serverAddress;

  public NetworkClient() {
    client = new Client();
    NetworkDataModel.register(client);
    initActions();
  }

  public void connect(ConnectionAddress addr) throws NetworkException {
    client.start();
    try {
      client.connect(5000, addr.getIp(), addr.getTcpPort(), addr.getUdpPort());
      serverAddress = addr;
      connected = true;
    } catch (IOException e) {
      e.printStackTrace();
      serverAddress = null;
      connected = false;
      throw new NetworkException("Unable to connect to host: " + addr.getIp() + " reason: " + e.getMessage());
    }
  }

  private void initActions() {
    client.addListener(new Listener() {
      @Override
      public void received(Connection conn, Object object) {
        if (object instanceof UDPMessage) {
          udpListener.receivedResponse(conn, (UDPMessage) object);
        } else if (object instanceof TCPMessage) {
          tcpListener.receivedResponse(conn, (TCPMessage) object);
        }
      }
    });
  }

  public void setUDPListener(UDPListener listener) {
    udpListener = listener;
  }

  public void setTCPListener(TCPListener listener) {
    tcpListener = listener;
  }

  public void sendTCPRequest(TCPMessage request) {
    client.sendTCP(request);
  }

  public void sendUDPRequest(TCPMessage request) {
    client.sendUDP(request);
  }

  public void disconnect() {
    client.stop();
    serverAddress = null;
    connected = false;
  }

  public boolean isConnected() {
    return connected;
  }

  public void setConnected(boolean connected) {
    this.connected = connected;
  }

  public static void main(String[] args) throws NetworkException, InterruptedException {
    NetworkClient client = new NetworkClient();
    client.setTCPListener(new TCPListener() {
      @Override
      public void receivedResponse(Connection conn, TCPMessage response) {
        System.out.println("Received Response");
        if (response instanceof ChatMessage) {
          ChatMessage msg = (ChatMessage) response;
          System.out.println("Received ChatMessage: " + msg.getText());
        } else {
          System.out.println("Received other type of response: " + response);
        }
      }

      @Override
      public void receivedRequest(Connection conn, TCPMessage request) {
      }
    });

    client.setUDPListener(new UDPListener() {
      @Override
      public void receivedResponse(Connection conn, UDPMessage response) {
        if (response instanceof GameSnapshot) {
          System.out.println("Received GameSnapshot:" + response);
        }
      }

      @Override
      public void receivedRequest(Connection conn, UDPMessage request) {
      }
    });
    client.connect(new ConnectionAddress("Game Server 1", "127.0.0.1", 55555, 55556));

    Thread.sleep(200);

    ChatMessage msg = new ChatMessage();
    msg.setText("Hello server!");
    client.sendTCPRequest(msg);

    Thread.sleep(20000);
  }

}
