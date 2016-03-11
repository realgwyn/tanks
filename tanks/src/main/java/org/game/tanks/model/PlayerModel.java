package org.game.tanks.model;

import java.awt.Polygon;
import java.io.Serializable;

public class PlayerModel implements Serializable {

  private static final long serialVersionUID = 5516126738056813345L;
  public int x;
  public int y;
  public int recentX;
  public int recentY;
  public float bodyAngle;
  public float towerAngle;
  public Polygon shape;

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

}
