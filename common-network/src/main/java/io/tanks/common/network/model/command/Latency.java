package io.tanks.common.network.model.command;

import io.tanks.common.network.model.Command;

public class Latency extends Command {

  private static final long serialVersionUID = -84092188908697380L;
  private long playerId;
  private int latency;

  public Latency() {

  }

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

  @Override
  public String toString() {
    return "Latency [playerId=" + playerId + ", latency=" + latency + "]";
  }

}
