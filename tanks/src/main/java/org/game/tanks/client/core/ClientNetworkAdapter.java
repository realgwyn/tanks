package org.game.tanks.client.core;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.game.tanks.network.NetworkAdapter;
import org.game.tanks.network.NetworkClient;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class ClientNetworkAdapter extends NetworkAdapter {

  private NetworkClient client;
  private final static Logger logger = Logger.getLogger(ClientNetworkAdapter.class);

  Connection serverConnection;

  public void connect(int udpPort) {
    // client.connect(addr);
  }

  public InetAddress discoverLanHost(int udpPort, int timeoutMillis) {
    return client.discoverHost(udpPort, timeoutMillis);
  }

  public void sendTCP(TCPMessage msg) {
    serverConnection.sendTCP(msg);
  }

  public void sendUDP(UDPMessage msg) {
    serverConnection.sendUDP(msg);
  }

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage message) {
    // TODO Auto-generated method stub

  }

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage message) {
    // TODO Auto-generated method stub

  }

  public Connection connectToServer(String serverAddress, int serverTcpPort, int serverUdpPort) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void connected(Connection c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void disconnected(Connection c) {
    // TODO Auto-generated method stub

  }

}
