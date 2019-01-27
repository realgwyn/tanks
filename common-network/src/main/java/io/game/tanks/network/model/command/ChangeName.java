package io.game.tanks.network.model.command;

import io.game.tanks.network.model.Command;

public class ChangeName extends Command {

  private static final long serialVersionUID = -8063453903578448944L;
  private long playerId;
  private String newPlayerName;

  public ChangeName() {

  }

  public long getPlayerId() {
    return playerId;
  }

  public ChangeName setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public String getNewPlayerName() {
    return newPlayerName;
  }

  public ChangeName setNewPlayerName(String playerName) {
    this.newPlayerName = playerName;
    return this;
  }

  @Override
  public String toString() {
    return "ChangeName [playerId=" + playerId + ", newPlayerName=" + newPlayerName + "]";
  }

}
