package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Connect extends Command {

  private static final long serialVersionUID = -7357290118820049157L;
  private long playerId;
  private String playerName;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

}
