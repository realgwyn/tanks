package org.game.tanks.network.model.command;

import java.io.Serializable;

public class PlayerStats implements Serializable {

  private static final long serialVersionUID = -5527049409093060600L;
  private long playerId;
  private int kills;
  private int deaths;
  private int ping;

  public long getPlayerId() {
    return playerId;
  }

  public PlayerStats setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public int getKills() {
    return kills;
  }

  public PlayerStats setKills(int kills) {
    this.kills = kills;
    return this;
  }

  public int getDeaths() {
    return deaths;
  }

  public PlayerStats setDeaths(int deaths) {
    this.deaths = deaths;
    return this;
  }

  public int getPing() {
    return ping;
  }

  public PlayerStats setPing(int ping) {
    this.ping = ping;
    return this;
  }

}
