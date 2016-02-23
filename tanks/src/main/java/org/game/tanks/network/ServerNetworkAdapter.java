package org.game.tanks.network;

import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.stereotype.Component;

@Component
public class ServerNetworkAdapter extends NetworkAdapter {

  private NetworkServer server;

  public NetworkServer getServer() {
    return server;
  }

  public void setServer(NetworkServer server) {
    this.server = server;
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

}
