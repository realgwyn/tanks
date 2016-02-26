package org.game.tanks.network.model;

public class Handshake extends TCPMessage {

  private static final long serialVersionUID = -7858479077369194127L;
  private long connectionID;
  private long playerId;
  private String playerName;

  public long getConnId() {
    return connectionID;
  }

  public Handshake setConnId(long connId) {
    this.connectionID = connId;
    return this;
  }

  public long getPlayerId() {
    return playerId;
  }

  public Handshake setPlayerId(long playerId) {
    this.playerId = playerId;
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
