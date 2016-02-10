package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class PlayersLatencyAll extends Command {

  private static final long serialVersionUID = 3102274103541476995L;
  private long id;
  private Ping[] playersLatency;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Ping[] getPlayersLatency() {
    return playersLatency;
  }

  public void setPlayersLatency(Ping[] playersLatency) {
    this.playersLatency = playersLatency;
  }

}
