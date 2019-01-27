package io.game.tanks.network.model.command;

import io.game.tanks.network.model.Command;

public class Connect extends Command {

  private static final long serialVersionUID = -7357290118820049157L;
  private int playerId;
  private String playerName;

  public Connect() {

  }

  public long getPlayerId() {
    return playerId;
  }

  public Connect setPlayerId(int playerId) {
    this.playerId = playerId;
    return this;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Connect setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

  @Override
  public String toString() {
    return "Connect [playerId=" + playerId + ", playerName=" + playerName + "]";
  }

}
