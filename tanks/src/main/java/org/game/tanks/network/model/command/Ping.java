package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Ping extends Command {

  private static final long serialVersionUID = -4325194568039883992L;
  private long playerId;
  private long sentTime;
  private int latency;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public long getSentTime() {
    return sentTime;
  }

  public void setSentTime(long sentTime) {
    this.sentTime = sentTime;
  }

  public int getLatency() {
    return latency;
  }

  public void setLatency(int latency) {
    this.latency = latency;
  }

}
