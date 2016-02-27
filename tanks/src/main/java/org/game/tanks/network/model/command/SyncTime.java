package org.game.tanks.network.model.command;

import java.util.Date;

import org.game.tanks.network.model.TCPMessage;

public class SyncTime extends TCPMessage {

  private static final long serialVersionUID = -3802970318039173341L;
  private Date matchStartTime;
  private Date matchEndTime;
  private Date roundEndTime;

  public Date getMatchStartTime() {
    return matchStartTime;
  }

  public SyncTime setMatchStartTime(Date matchStartTime) {
    this.matchStartTime = matchStartTime;
    return this;
  }

  public Date getMatchEndTime() {
    return matchEndTime;
  }

  public SyncTime setMatchEndTime(Date matchEndTime) {
    this.matchEndTime = matchEndTime;
    return this;
  }

  public Date getRoundEndTime() {
    return roundEndTime;
  }

  public SyncTime setRoundEndTime(Date roundEndTime) {
    this.roundEndTime = roundEndTime;
    return this;
  }

}
