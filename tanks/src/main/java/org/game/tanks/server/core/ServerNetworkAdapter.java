package org.game.tanks.server.core;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.network.NetworkAdapter;
import org.game.tanks.network.NetworkServer;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.utils.NetworkMessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class ServerNetworkAdapter extends NetworkAdapter {

  @Autowired
  ServerContext ctx;
  @Autowired
  Config config;
  @Autowired
  NetworkMessageValidator networkMessageValidator;

  private boolean packetValidatorEnabled;

  @PostConstruct
  public void init() {
    packetValidatorEnabled = config.getPropertyBoolean(Config.SERVER_ENABLE_PACKET_VALIDATION);
  }

  private NetworkServer server;

  public NetworkServer getServer() {
    return server;
  }

  public void setServer(NetworkServer server) {
    this.server = server;
    this.server.setTCPListener(this);
    this.server.setUDPListener(this);
  }

  public void sendTCP(Connection con, TCPMessage msg) {
    con.sendTCP(msg);
  }

  public void sendTCP(PlayerServerModel player, TCPMessage msg) {
    player.getConnection().sendTCP(msg);
  }

  public void sendUDP(PlayerServerModel player, UDPMessage msg) {
    player.getConnection().sendUDP(msg);
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

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage message) {
    if (packetValidatorEnabled) {
      validateAndProcessIncomingMessage(conn, message);
    } else {
      if (message instanceof GameEvent) {
        ctx.getIncomingGameEvents().add((GameEvent) message);
      } else if (message instanceof Command) {
        ctx.getIncomingCommands().add((Command) message);
      } else if (message instanceof CommunicationMessage) {
        ctx.getIncomingCommunicationMessages().add((CommunicationMessage) message);
      } else if (message instanceof AdminCommand) {
        ctx.getIncomingAdminCommands().add((AdminCommand) message);
      } else {
        throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
      }
    }
  }

  private void validateAndProcessIncomingMessage(Connection conn, TCPMessage message) {
    if (message instanceof GameEvent) {
      GameEvent gameEvent = (GameEvent) message;
      if (networkMessageValidator.isValid(conn, gameEvent)) {
        ctx.getIncomingGameEvents().add(gameEvent);
      }
    } else if (message instanceof Command) {
      Command command = (Command) message;
      if (networkMessageValidator.isValid(conn, command)) {
        ctx.getIncomingCommands().add(command);
      }
    } else if (message instanceof CommunicationMessage) {
      CommunicationMessage comMsg = (CommunicationMessage) message;
      if (networkMessageValidator.isValid(conn, comMsg)) {
        ctx.getIncomingCommunicationMessages().add(comMsg);
      }
    } else if (message instanceof AdminCommand) {
      AdminCommand admCmd = (AdminCommand) message;
      if (networkMessageValidator.isValid(conn, admCmd)) {
        ctx.getIncomingAdminCommands().add(admCmd);
      }
    } else {
      throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
    }
  }

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage message) {
    if (message instanceof PlayerSnapshot) {
      ctx.getIncomingPlayerSnapshots().add((PlayerSnapshot) message);
    } else {
      throw new UnsupportedOperationException("Unsupported Message type: " + message.getClass().getSimpleName());
    }
  }

  public boolean hasNewConnections() {
    return !server.getIncommingConnections().isEmpty();
  }

  public Connection pollNewConnection() {
    return server.getIncommingConnections().poll();
  }

  public boolean hasClosedConnections() {
    return !server.getClosedConnections().isEmpty();
  }

  public Connection pollClosedConnection() {
    return server.getClosedConnections().poll();
  }

}
