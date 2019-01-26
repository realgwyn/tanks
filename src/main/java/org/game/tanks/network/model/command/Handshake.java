package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Handshake extends Command {

  private static final long serialVersionUID = -7858479077369194127L;
  private long playerConnectionId;
  private String playerName;

  public Handshake() {

  }

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

  @Override
  public String toString() {
    return "Handshake [playerConnectionId=" + playerConnectionId + ", playerName=" + playerName + "]";
  }

}
