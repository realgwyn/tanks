package org.game.tanks.server.model;

import java.awt.Polygon;

import org.game.tanks.network.model.udp.PlayerPosition;

import com.esotericsoftware.kryonet.Connection;

public class PlayerServerModel {

  public enum PlayerState {
    ALIVE, DEAD
  }

  public long sequenceNumber;
  public boolean sequenceFlipFlag;
  public PlayerPosition playerPosition;
  public int recentX;
  public int recentY;
  public Polygon shape;
  public long playerId;

  private Connection connection;
  private int rankNumber;
  private String playerName;
  private PlayerState state;
  private float health;
  private int kills;
  private int deaths;
  private int team;
  private int latency;

  public PlayerServerModel(long playerId) {
    this.playerId = playerId;
    this.playerPosition = new PlayerPosition();
    this.playerPosition.id = playerId;
  }

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public long getPlayerId() {
    return playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getPositionX() {
    return playerPosition.x;
  }

  public void setPositionX(int positionX) {
    this.playerPosition.x = positionX;
  }

  public int getPositionY() {
    return playerPosition.y;
  }

  public void setPositionY(int positionY) {
    this.playerPosition.y = positionY;
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

  public long getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(long sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public float getBodyAngle() {
    return playerPosition.bodyAngle;
  }

  public void setBodyAngle(float bodyAngle) {
    playerPosition.bodyAngle = bodyAngle;
  }

  public float getTowerAngle() {
    return playerPosition.towerAngle;
  }

  public void setTowerAngle(float towerAngle) {
    playerPosition.towerAngle = towerAngle;
  }

  public PlayerState getState() {
    return state;
  }

  public void setState(PlayerState state) {
    this.state = state;
  }

  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  @Override
  public String toString() {
    return "Player [id:" + playerId + ", name:" + playerName + "]";
  }

}
