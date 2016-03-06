package org.game.tanks.network.model;

public class Handshake extends TCPMessage {

  private static final long serialVersionUID = -7858479077369194127L;
  private long playerConnectionId;
  private String playerName;

  public long getPlayerConnectionId() {
    return playerConnectionId;
  }

  public Handshake setPlayerConnectionId(long playerId) {
    this.playerConnectionId = playerId;
    return this;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Handshake setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

}
