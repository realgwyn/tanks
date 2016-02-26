package org.game.tanks.network.model;

public class Command extends TCPMessage {

  private static final long serialVersionUID = -7480487174538122148L;

  private long playerToId;

  public long getPlayerToId() {
    return playerToId;
  }

  public void setPlayerToId(long playerToId) {
    this.playerToId = playerToId;
  }

}
