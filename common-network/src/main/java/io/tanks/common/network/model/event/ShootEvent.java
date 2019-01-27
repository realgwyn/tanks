package io.tanks.common.network.model.event;

import java.awt.geom.Point2D;

import io.tanks.common.network.model.GameEvent;

public class ShootEvent extends GameEvent {

  private static final long serialVersionUID = 1487022198642214486L;
  private long playerId;
  private Point2D point;
  private float damage;

  public ShootEvent() {

  }

  public long getPlayerId() {
    return playerId;
  }

  public ShootEvent setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public Point2D getPoint() {
    return point;
  }

  public ShootEvent setPoint(Point2D point) {
    this.point = point;
    return this;
  }

  public float getDamage() {
    return damage;
  }

  public ShootEvent setDamage(float damage) {
    this.damage = damage;
    return this;
  }

  @Override
  public String toString() {
    return "ShootEvent [playerId=" + playerId + ", point=" + point + ", damage=" + damage + "]";
  }

}
