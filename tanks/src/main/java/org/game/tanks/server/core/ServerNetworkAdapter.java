package org.game.tanks.server.core;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class ServerNetworkAdapter extends NetworkAdapter {

  @Autowired
  ServerContext ctx;

  private NetworkServer server;

  public NetworkServer getServer() {
    return server;
  }

  public void setServer(NetworkServer server) {
    this.server = server;
    this.server.setTCPListener(this);
    this.server.setUDPListener(this);
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
