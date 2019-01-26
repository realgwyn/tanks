package org.game.tanks.network.model;

public class Command extends TCPMessage {

  private static final long serialVersionUID = -7480487174538122148L;

  private int playerToId;

  public int getPlayerToId() {
    return playerToId;
  }

  public void setPlayerToId(int playerToId) {
    this.playerToId = playerToId;
  }

}
