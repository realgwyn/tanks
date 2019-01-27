package io.tanks.client.core;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

import io.tanks.client.cfg.ClientConfig;
import io.tanks.common.network.ConnectionAddress;
import io.tanks.common.network.NetworkAdapter;
import io.tanks.common.network.NetworkClient;
import io.tanks.common.network.NetworkException;
import io.tanks.common.network.model.Command;
import io.tanks.common.network.model.CommunicationMessage;
import io.tanks.common.network.model.GameEvent;
import io.tanks.common.network.model.TCPMessage;
import io.tanks.common.network.model.UDPMessage;
import io.tanks.common.network.model.udp.GameSnapshot;

@Component
public class ClientNetworkAdapter extends NetworkAdapter {

  private final static Logger logger = Logger.getLogger(ClientNetworkAdapter.class);

  @Autowired
  ClientContext ctx;
  @Autowired
  ClientEventBus bus;
  @Autowired
  ClientConfig cfg;

  private NetworkClient client;
  private int defaultServerUdpPort;
  private int defaultServerTcpPort;
  private boolean offlineDebugModeEnabled;
  private boolean networkDebugModeEnabled;
  private boolean connected;

  @PostConstruct
  public void init() {
    defaultServerTcpPort = cfg.getPropertyInt(ClientConfig.SERVER_DEFAULT_TCP_PORT);
    defaultServerUdpPort = cfg.getPropertyInt(ClientConfig.SERVER_DEFAULT_UDP_PORT);
    offlineDebugModeEnabled = cfg.getPropertyBoolean(ClientConfig.SERVER_OFFLINE_DEBUG_MODE);
    networkDebugModeEnabled = cfg.getPropertyBoolean(ClientConfig.GAME_ENABLE_NETWORK_DEBUG_MODE);
  }

  public void setClient(NetworkClient client) {
    this.client = client;
    this.client.setConnectionListener(this);
    this.client.setTCPListener(this);
    this.client.setUDPListener(this);
  }

  public void connectToServer(String hostAddress) throws NetworkException {
    ConnectionAddress addr = new ConnectionAddress()
        .setAddress(hostAddress)
        .setTcpPort(defaultServerTcpPort)
        .setUdpPort(defaultServerUdpPort);
    connectToServer(addr);
  }

  public void connectToServer(ConnectionAddress addr) throws NetworkException {
    logger.debug("Connecting to Server: " + addr);
    client.connect(addr);
  }

  public void disconnect() {
    logger.debug("Disconnecting from Server");
    client.disconnect();
    connected = false;
  }

  public InetAddress discoverLanHost(int udpPort, int timeoutMillis) {
    if (offlineDebugModeEnabled) {
      return null;
    }
    return client.discoverHost(udpPort, timeoutMillis);
  }

  public void sendTCP(TCPMessage msg) {
    if (offlineDebugModeEnabled) {
      logger.debug("-> TCP: " + msg.toString());
    } else {
      if (networkDebugModeEnabled) {
        logger.debug("-> TCP: " + msg.toString());
      }
      client.sendTCP(msg);
    }
  }

  public void sendUDP(UDPMessage msg) {
    if (offlineDebugModeEnabled) {
      logger.debug("-> UDP: " + msg.toString());
    } else {
      client.sendUDP(msg);
    }
  }

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage message) {
    if (offlineDebugModeEnabled) {
      logger.debug("<- UDP: " + message.toString());
    } else {
      if (message instanceof GameSnapshot) {
        bus.getIncomingGameSnapshots().add((GameSnapshot) message);
      } else {
        throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
      }
    }
  }

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage message) {
    if (offlineDebugModeEnabled) {
      logger.debug("<- TCP: " + message.toString());
    } else {
      if (networkDebugModeEnabled) {
        logger.debug("<- TCP: " + message.toString());
      }
      if (message instanceof GameEvent) {
        bus.getIncomingGameEvents().add((GameEvent) message);
      } else if (message instanceof Command) {
        bus.getIncomingCommands().add((Command) message);
      } else if (message instanceof CommunicationMessage) {
        bus.getIncomingMessages().add((CommunicationMessage) message);
      } else {
        throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
      }
    }
  }

  @Override
  public void connected(Connection c) {
    logger.debug("Connected to Server " + c.getRemoteAddressTCP());
    connected = true;
  }

  @Override
  public void disconnected(Connection c) {
    logger.debug("Disconnected from Server " + c.getRemoteAddressTCP());
    connected = false;
  }

  public boolean isConnected() {
    return connected;
  }

}
