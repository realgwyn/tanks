package io.tanks.server.core;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

import io.tanks.common.network.ConnectionInfo;
import io.tanks.common.network.NetworkAdapter;
import io.tanks.common.network.NetworkServer;
import io.tanks.common.network.model.AdminCommand;
import io.tanks.common.network.model.Command;
import io.tanks.common.network.model.CommunicationMessage;
import io.tanks.common.network.model.GameEvent;
import io.tanks.common.network.model.TCPMessage;
import io.tanks.common.network.model.UDPMessage;
import io.tanks.common.network.model.command.Handshake;
import io.tanks.common.network.model.udp.PlayerSnapshot;
import io.tanks.server.config.ServerConfig;
import io.tanks.server.core.task.TaskManager;
import io.tanks.server.service.NetworkMessageValidator;

@Component
public class ServerNetworkAdapter extends NetworkAdapter {

  private NetworkServer server;
  private final static Logger logger = Logger.getLogger(ServerNetworkAdapter.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerEventBus bus;
  @Autowired
  ServerConfig config;
  @Autowired
  NetworkMessageValidator networkMessageValidator;
  @Autowired
  TaskManager taskManager;

  private boolean offlineDebugMode;
  private boolean networkDebugMode;

  @PostConstruct
  public void init() {
    offlineDebugMode = config.isOfflineDebugMode();
    networkDebugMode = config.isNetworkDebugMode();
  }

  public NetworkServer getServer() {
    return server;
  }

  public void setServer(NetworkServer server) {
    this.server = server;
    this.server.setConnectionListener(this);
    this.server.setTCPListener(this);
    this.server.setUDPListener(this);
  }

  public void sendToAllTCP(TCPMessage msg) {
    if (offlineDebugMode) {
      logger.debug("-> TCPbroadcast " + msg.toString());
    } else {
      if (networkDebugMode) {
        logger.debug("-> TCPbroadcast " + msg.toString());
      }
      server.sendToAllTCP(msg);
    }
  }

  public void sendToAllUDP(UDPMessage msg) {
    server.sendToAllUDP(msg);
  }

  public void sendToAllExceptTCP(int connectionID, TCPMessage msg) {
    if (offlineDebugMode) {
      logger.debug("-> TCPxor(id:" + connectionID + ")" + msg.toString());
    } else {
      if (networkDebugMode) {
        logger.debug("-> TCPxor(id:" + connectionID + ")" + msg.toString());
      }
      server.sendToAllExceptTCP(connectionID, msg);
    }
  }

  public void sendToAllExceptUDP(int connectionID, UDPMessage msg) {
    server.sendToAllExceptUDP(connectionID, msg);
  }

  public void sendTCP(int connectionID, TCPMessage msg) {
    if (offlineDebugMode) {
      logger.debug("-> TCP(id:" + connectionID + ")" + msg.toString());
    } else {
      if (networkDebugMode) {
        logger.debug("-> TCP(id:" + connectionID + ")" + msg.toString());
      }
      server.sendTCP(connectionID, msg);
    }
  }

  public void sendUDP(int connectionID, TCPMessage msg) {
    server.sendUDP(connectionID, msg);
  }

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage message) {
    if (offlineDebugMode) {
      logger.debug("<- TCP(id:" + conn.getID() + ")" + message.toString());
    } else {
      if (networkDebugMode) {
        logger.debug("<- TCP(id:" + conn.getID() + ")" + message.toString());
      }
      validateAndProcessIncomingMessage(conn, message);
    }
  }

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage message) {
    if (ctx.getNewRoundFlipFlag() == message.getNewRoundFlipFlag()) {
      if (message instanceof PlayerSnapshot) {
        bus.getIncomingPlayerSnapshots().add((PlayerSnapshot) message);
      } else {
        throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
      }
    }
  }

  @Override
  public void connected(Connection c) {
    ConnectionInfo connectionInfo = new ConnectionInfo()
        .setConnectionId(c.getID())
        .setNetworkAddress(c.getRemoteAddressTCP().getHostName());
    logger.debug("Incomming Player " + connectionInfo);
    server.getIncommingConnections().add(connectionInfo);
  }

  @Override
  public void disconnected(Connection c) {
    logger.debug("Leaving Player (id:" + c.getID() + ")");
    server.getClosedConnections().add(c.getID());
  }

  public boolean hasNewConnections() {
    return !server.getIncommingConnections().isEmpty();
  }

  public ConnectionInfo pollNewConnection() {
    return server.getIncommingConnections().poll();
  }

  public boolean hasClosedConnections() {
    return !server.getClosedConnections().isEmpty();
  }

  public Integer pollClosedConnectionId() {
    return server.getClosedConnections().poll();
  }

  public String getIpAddressByConnectionId(int connectionIdFrom) {
    Connection conn = server.getConnectionById(connectionIdFrom);
    if (conn != null) {
      return conn.getRemoteAddressTCP().getHostName();
    }
    return null;
  }

  private void validateAndProcessIncomingMessage(Connection conn, TCPMessage message) {
    if (message instanceof GameEvent) {
      GameEvent gameEvent = (GameEvent) message;
      if (networkMessageValidator.isValid(conn, gameEvent)) {
        bus.getIncomingGameEvents().add(gameEvent);
      }
    } else if (message instanceof Command) {
      if (message instanceof Handshake) {
        bus.getIncomingHandshakes().add((Handshake) message);
      } else {
        Command command = (Command) message;
        if (networkMessageValidator.isValid(conn, command)) {
          bus.getIncomingCommands().add(command);
        }
      }
    } else if (message instanceof CommunicationMessage) {
      CommunicationMessage comMsg = (CommunicationMessage) message;
      if (networkMessageValidator.isValid(conn, comMsg)) {
        bus.getIncomingCommunicationMessages().add(comMsg);
      }
    } else if (message instanceof AdminCommand) {
      AdminCommand admCmd = (AdminCommand) message;
      if (networkMessageValidator.isValid(conn, admCmd)) {
        bus.getIncomingAdminCommands().add(admCmd);
      }
    } else {
      throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
    }
  }

}
