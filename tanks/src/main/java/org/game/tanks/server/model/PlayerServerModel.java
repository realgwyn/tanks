package org.game.tanks.server.model;

import org.game.tanks.model.PlayerModel;

import com.esotericsoftware.kryonet.Connection;

public class PlayerServerModel extends PlayerModel {

  private static final long serialVersionUID = 5787415399283390781L;
  public long sequenceNumber;
  public boolean sequenceFlipFlag;
  private int latency;

  private int rankNumber;
  private int kills;
  private int deaths;

  private Connection connection;
  private boolean connectionEstablished;
  public int recentX;
  public int recentY;

  public PlayerServerModel(long playerId, Connection connection) {
    this.playerId = playerId;
    this.connection = connection;
  }

  public Connection getConnection() {
    return connection;
  }

  public PlayerServerModel setConnection(Connection connection) {
    this.connection = connection;
    return this;
  }

  public int getKills() {
    return kills;
  }

  public PlayerServerModel setKills(int kills) {
    this.kills = kills;
    return this;
  }

  public int getDeaths() {
    return deaths;
  }

  public PlayerServerModel setDeaths(int deaths) {
    this.deaths = deaths;
    return this;
  }

  public int getLatency() {
    return latency;
  }

  public PlayerServerModel setLatency(int latency) {
    this.latency = latency;
    return this;
  }

  public int getRankNumber() {
    return rankNumber;
  }

  public PlayerServerModel setRankNumber(int rankNumber) {
    this.rankNumber = rankNumber;
    return this;
  }

  public long getSequenceNumber() {
    return sequenceNumber;
  }

  public PlayerServerModel setSequenceNumber(long sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    return this;
  }

  public boolean isConnectionEstablished() {
    return connectionEstablished;
  }

  public PlayerServerModel setConnectionEstablished(boolean connectionEstablished) {
    this.connectionEstablished = connectionEstablished;
    return this;
  }

  @Override
  public String toString() {
    return "Player [id:" + playerId + ", name:" + playerName + "]";
  }

}
