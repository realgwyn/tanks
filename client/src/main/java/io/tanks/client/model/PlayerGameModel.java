package io.tanks.client.model;

import io.tanks.common.network.model.game.PlayerModel;

public class PlayerGameModel {

  private static final long serialVersionUID = -1688914743444679357L;
  private long playerID;
  private String playerName;
  private PlayerModel model;
  private int latency;

  private int rankNumber;
  private int kills;
  private int deaths;

  public int getLatency() {
    return latency;
  }

  public PlayerGameModel setLatency(int latency) {
    this.latency = latency;
    return this;
  }

  public int getRankNumber() {
    return rankNumber;
  }

  public PlayerGameModel setRankNumber(int rankNumber) {
    this.rankNumber = rankNumber;
    return this;
  }

  public int getKills() {
    return kills;
  }

  public PlayerGameModel setKills(int kills) {
    this.kills = kills;
    return this;
  }

  public int getDeaths() {
    return deaths;
  }

  public PlayerGameModel setDeaths(int deaths) {
    this.deaths = deaths;
    return this;
  }

  public long getPlayerID() {
    return playerID;
  }

  public PlayerGameModel setPlayerID(long playerID) {
    this.playerID = playerID;
    return this;
  }

  public String getPlayerName() {
    return playerName;
  }

  public PlayerGameModel setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

  public PlayerModel getModel() {
    return model;
  }

  public PlayerGameModel setModel(PlayerModel model) {
    this.model = model;
    return this;
  }

}
