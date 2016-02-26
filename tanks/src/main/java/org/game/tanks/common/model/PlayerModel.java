package org.game.tanks.common.model;

import java.awt.Polygon;
import java.io.Serializable;

public class PlayerModel implements Serializable {

  private static final long serialVersionUID = 5516126738056813345L;
  public long playerId;
  public String playerName = "Unknown";
  public int x;
  public int y;
  public float bodyAngle;
  public float towerAngle;
  public Polygon shape;
  public PlayerState state = PlayerState.UNDEFINED;
  public float health;
  public int team;

  public long getPlayerId() {
    return playerId;
  }

  public PlayerModel setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public String getPlayerName() {
    return playerName;
  }

  public PlayerModel setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

  public int getX() {
    return x;
  }

  public PlayerModel setX(int x) {
    this.x = x;
    return this;
  }

  public int getY() {
    return y;
  }

  public PlayerModel setY(int y) {
    this.y = y;
    return this;
  }

  public float getBodyAngle() {
    return bodyAngle;
  }

  public PlayerModel setBodyAngle(float bodyAngle) {
    this.bodyAngle = bodyAngle;
    return this;
  }

  public float getTowerAngle() {
    return towerAngle;
  }

  public PlayerModel setTowerAngle(float towerAngle) {
    this.towerAngle = towerAngle;
    return this;
  }

  public Polygon getShape() {
    return shape;
  }

  public PlayerModel setShape(Polygon shape) {
    this.shape = shape;
    return this;
  }

  public PlayerState getState() {
    return state;
  }

  public PlayerModel setState(PlayerState state) {
    this.state = state;
    return this;
  }

  public float getHealth() {
    return health;
  }

  public PlayerModel setHealth(float health) {
    this.health = health;
    return this;
  }

  public int getTeam() {
    return team;
  }

  public PlayerModel setTeam(int team) {
    this.team = team;
    return this;
  }

}
