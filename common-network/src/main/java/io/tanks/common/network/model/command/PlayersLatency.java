package io.tanks.common.network.model.command;

import java.util.Arrays;

import io.tanks.common.network.model.Command;

public class PlayersLatency extends Command {

  private static final long serialVersionUID = -8531753145720959806L;
  private Latency[] playersLatency;

  public PlayersLatency() {

  }

  public Latency[] getPlayersLatency() {
    return playersLatency;
  }

  public PlayersLatency setPlayersLatency(Latency[] playersLatency) {
    this.playersLatency = playersLatency;
    return this;
  }

  @Override
  public String toString() {
    return "PlayersLatency [playersLatency=" + Arrays.toString(playersLatency) + "]";
  }

}
