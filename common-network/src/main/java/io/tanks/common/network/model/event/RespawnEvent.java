package io.tanks.common.network.model.event;

import io.tanks.common.network.model.GameEvent;

public class RespawnEvent extends GameEvent {

  private static final long serialVersionUID = -4126883379693676031L;
  private long playerId;
  private int posX;
  private int posY;

  public RespawnEvent() {

  }

  public long getPlayerId() {
    return playerId;
  }

  public RespawnEvent setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public int getPosX() {
    return posX;
  }

  public RespawnEvent setPosX(int posX) {
    this.posX = posX;
    return this;
  }

  public int getPosY() {
    return posY;
  }

  public RespawnEvent setPosY(int posY) {
    this.posY = posY;
    return this;
  }

  @Override
  public String toString() {
    return "RespawnEvent [playerId=" + playerId + ", posX=" + posX + ", posY=" + posY + "]";
  }

}
