package org.game.tanks.network;

import java.io.IOException;

import org.game.tanks.network.model.NetworkDataModel;
import org.game.tanks.network.model.PacketType;
import org.game.tanks.network.model.TCPRequest;
import org.game.tanks.network.model.TCPResponse;
import org.game.tanks.network.model.UDPRequest;
import org.game.tanks.network.model.UDPResponse;

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
      public void received(Connection conn, Object object) {
        if (object instanceof UDPResponse) {
          udpListener.receivedResponse(conn, (UDPResponse) object);
        } else if (object instanceof TCPResponse) {
          tcpListener.receivedResponse(conn, (TCPResponse) object);
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

  public void sendTCPRequest(TCPRequest request) {
    client.sendTCP(request);
  }

  public void sendUDPRequest(UDPResponse response) {
    client.sendUDP(response);
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
      public void receivedResponse(Connection conn, TCPResponse response) {
        System.out.println("Received Response");
        if (response.getType() == PacketType.MESSAGE) {
          System.out.println("Received response.data.string:" + new String(response.getBytes()));
        } else if (response.getType() == PacketType.SNAPSHOT) {
          System.out.println("Received response.data:" + response);
        }
      }

      public void receivedRequest(Connection conn, TCPRequest request) {}
    });

    client.setUDPListener(new UDPListener() {
      public void receivedResponse(Connection conn, UDPResponse response) {
        if (response.getType() == PacketType.SNAPSHOT) {
          System.out.println("Received response.data:" + response);
        }
      }
      public void receivedRequest(Connection conn, UDPRequest request) {}
    });
    client.connect(new ConnectionAddress("Game Server 1", "127.0.0.1", 55555, 55556));

    Thread.sleep(200);

    client.sendTCPRequest(new TCPRequest(PacketType.MESSAGE, "Hello".getBytes()));

    Thread.sleep(20000);
  }

}
