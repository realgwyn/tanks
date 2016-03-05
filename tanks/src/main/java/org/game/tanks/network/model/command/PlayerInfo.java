package org.game.tanks.network.model.command;

import java.io.Serializable;

import org.game.tanks.model.PlayerModel;

public class PlayerInfo implements Serializable {

  private static final long serialVersionUID = -5527049409093060600L;
  private PlayerModel model;
  private int name;
  private long playerId;
  private int kills;
  private int deaths;
  private int latency;

  public PlayerModel getModel() {
    return model;
  }

  public PlayerInfo setModel(PlayerModel model) {
    this.model = model;
    return this;
  }

  public int getKills() {
    return kills;
  }

  public PlayerInfo setKills(int kills) {
    this.kills = kills;
    return this;
  }

  public int getDeaths() {
    return deaths;
  }

  public PlayerInfo setDeaths(int deaths) {
    this.deaths = deaths;
    return this;
  }

  public int getLatency() {
    return latency;
  }

  public PlayerInfo setLatency(int latency) {
    this.latency = latency;
    return this;
  }

  public int getName() {
    return name;
  }

  public PlayerInfo setName(int name) {
    this.name = name;
    return this;
  }

  public long getPlayerId() {
    return playerId;
  }

  public PlayerInfo setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

}
