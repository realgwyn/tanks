package org.game.tanks.network.model.event;

import java.awt.geom.Point2D;

import org.game.tanks.network.model.GameEvent;

public class ShootEvent extends GameEvent {

  private static final long serialVersionUID = 1487022198642214486L;
  private long playerId;
  private Point2D point;
  private float damage;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public Point2D getPoint() {
    return point;
  }

  public void setPoint(Point2D point) {
    this.point = point;
  }

  public float getDamage() {
    return damage;
  }

  public void setDamage(float damage) {
    this.damage = damage;
  }

}
