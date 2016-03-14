package org.game.tanks.network.model.command;

import java.io.Serializable;

import org.game.tanks.model.PlayerState;

public class PlayerInfo implements Serializable {

  private static final long serialVersionUID = -5527049409093060600L;
  private String playerName = "Unnamed";
  private int playerId;
  private int kills;
  private int deaths;
  private int latency;
  private PlayerState state = PlayerState.DEAD;
  private int team;
  private float health;

  public PlayerInfo() {
  }

  public PlayerInfo(int playerId) {
    this.playerId = playerId;
  }

  public void update(PlayerInfo info) {
    playerName = info.getPlayerName();
    kills = info.getKills();
    deaths = info.getDeaths();
    latency = info.getLatency();
    state = info.getState();
    team = info.getTeam();
    health = info.getHealth();
  }

  public PlayerInfo incrementKills() {
    this.kills++;
    return this;
  }

  public int getKills() {
    return kills;
  }

  public PlayerInfo setKills(int kills) {
    this.kills = kills;
    return this;
  }

  public PlayerInfo incrementDeaths() {
    this.deaths++;
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

  public String getPlayerName() {
    return playerName;
  }

  public PlayerInfo setPlayerName(String name) {
    this.playerName = name;
    return this;
  }

  public int getPlayerId() {
    return playerId;
  }

  public PlayerState getState() {
    return state;
  }

  public PlayerInfo setState(PlayerState state) {
    this.state = state;
    return this;
  }

  public int getTeam() {
    return team;
  }

  public PlayerInfo setTeam(int team) {
    this.team = team;
    return this;
  }

  public float getHealth() {
    return health;
  }

  public PlayerInfo setHealth(float health) {
    this.health = health;
    return this;
  }

  public PlayerInfo appendHealth(float health) {
    this.health += health;
    return this;
  }

}
