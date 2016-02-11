package org.game.tanks.server.model;

import java.awt.Polygon;

import com.esotericsoftware.kryonet.Connection;

public class PlayerServerModel {

  private Connection connection;
  private int rankNumber;
  private long playerId;
  private String playerName;
  private int positionX;
  private int positionY;
  private Polygon shape;
  private int kills;
  private int deaths;
  private int team;
  private int latency;

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
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

  public int getTeam() {
    return team;
  }

  public void setTeam(int team) {
    this.team = team;
  }

  public int getLatency() {
    return latency;
  }

  public void setLatency(int latency) {
    this.latency = latency;
  }

  public int getRankNumber() {
    return rankNumber;
  }

  public void setRankNumber(int rankNumber) {
    this.rankNumber = rankNumber;
  }

  public Polygon getShape() {
    return shape;
  }

  public void setShape(Polygon shape) {
    this.shape = shape;
  }

}
