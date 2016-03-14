package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class SyncTime extends Command {

  private static final long serialVersionUID = -3802970318039173341L;
  private long matchStartTime;
  private long matchEndTime;
  private long roundEndTime;

  public long getMatchStartTime() {
    return matchStartTime;
  }

  public SyncTime setMatchStartTime(long matchStartTime) {
    this.matchStartTime = matchStartTime;
    return this;
  }

  public long getMatchEndTime() {
    return matchEndTime;
  }

  public SyncTime setMatchEndTime(long matchEndTime) {
    this.matchEndTime = matchEndTime;
    return this;
  }

  public long getRoundEndTime() {
    return roundEndTime;
  }

  public SyncTime setRoundEndTime(long roundEndTime) {
    this.roundEndTime = roundEndTime;
    return this;
  }

  @Override
  public String toString() {
    return "SyncTime [matchStartTime=" + matchStartTime + ", matchEndTime=" + matchEndTime + ", roundEndTime=" + roundEndTime + "]";
  }

}
