package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class ChooseTeam extends Command {

  private static final long serialVersionUID = -1066132452923893284L;
  private long playerId;
  private int team;

  public long getPlayerId() {
    return playerId;
  }

  public ChooseTeam setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public int getTeam() {
    return team;
  }

  public ChooseTeam setTeam(int team) {
    this.team = team;
    return this;
  }

  @Override
  public String toString() {
    return "ChooseTeam [playerId=" + playerId + ", team=" + team + "]";
  }

}
