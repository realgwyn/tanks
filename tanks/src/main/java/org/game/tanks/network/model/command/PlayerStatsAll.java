package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class PlayerStatsAll extends Command {

  private static final long serialVersionUID = -2910293749094145629L;
  private PlayerStats[] allStats;

  public PlayerStats[] getAllStats() {
    return allStats;
  }

  public void setAllStats(PlayerStats[] allStats) {
    this.allStats = allStats;
  }

}
