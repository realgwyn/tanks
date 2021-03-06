package io.tanks.common.network.model.event;

import io.tanks.common.network.model.GameEvent;

public class HitEvent extends GameEvent {

  private static final long serialVersionUID = -4190297440101108657L;
  private long playerId;
  private float damage;

  public HitEvent() {

  }

  public long getPlayerId() {
    return playerId;
  }

  public HitEvent setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public float getDamage() {
    return damage;
  }

  public HitEvent setDamage(float damage) {
    this.damage = damage;
    return this;
  }

  @Override
  public String toString() {
    return "HitEvent [playerId=" + playerId + ", damage=" + damage + "]";
  }

}
