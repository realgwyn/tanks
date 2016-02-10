package org.game.tanks.network.model.command;

import java.io.Serializable;

public class PlayerStats implements Serializable {

  private static final long serialVersionUID = -5527049409093060600L;
  private long playerId;
  private int kills;
  private int deaths;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public int getKills() {
    return kills;
  }

  public void setKills(int kills) {
    this.kills = kills;
  }

  public int getDeaths() {
    return deaths;
  }

  public void setDeaths(int deaths) {
    this.deaths = deaths;
  }

}
