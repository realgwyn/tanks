package org.game.tanks.client.model;

import org.game.tanks.model.PlayerModel;

public class PlayerGameModel extends PlayerModel {

  private static final long serialVersionUID = -1688914743444679357L;
  public long sequenceNumber;
  public boolean sequenceFlipFlag;
  private int latency;

  private int rankNumber;
  private int kills;
  private int deaths;
  private int team;

  public long getSequenceNumber() {
    return sequenceNumber;
  }

  public PlayerGameModel setSequenceNumber(long sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    return this;
  }

  public boolean isSequenceFlipFlag() {
    return sequenceFlipFlag;
  }

  public PlayerGameModel setSequenceFlipFlag(boolean sequenceFlipFlag) {
    this.sequenceFlipFlag = sequenceFlipFlag;
    return this;
  }

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

  @Override
  public int getTeam() {
    return team;
  }

  @Override
  public PlayerGameModel setTeam(int team) {
    this.team = team;
    return this;
  }

}
