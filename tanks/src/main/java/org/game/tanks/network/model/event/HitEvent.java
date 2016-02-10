package org.game.tanks.network.model.event;

import org.game.tanks.network.model.GameEvent;

public class HitEvent extends GameEvent {

  private static final long serialVersionUID = -4190297440101108657L;
  private long playerId;
  private float damage;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public float getDamage() {
    return damage;
  }

  public void setDamage(float damage) {
    this.damage = damage;
  }

}
