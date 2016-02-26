package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Latency extends Command {

  private static final long serialVersionUID = -84092188908697380L;
  private long playerId;
  private int latency;

  public long getPlayerId() {
    return playerId;
  }

  public Latency setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public int getLatency() {
    return latency;
  }

  public Latency setLatency(int latency) {
    this.latency = latency;
    return this;
  }

}
