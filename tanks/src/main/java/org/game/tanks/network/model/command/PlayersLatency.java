package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class PlayersLatency extends Command {

  private static final long serialVersionUID = -8531753145720959806L;
  private Latency[] playersLatency;

  public Latency[] getPlayersLatency() {
    return playersLatency;
  }

  public PlayersLatency setPlayersLatency(Latency[] playersLatency) {
    this.playersLatency = playersLatency;
    return this;
  }

}
