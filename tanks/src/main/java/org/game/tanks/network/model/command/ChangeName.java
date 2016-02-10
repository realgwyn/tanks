package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class ChangeName extends Command {

  private static final long serialVersionUID = -8063453903578448944L;
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
