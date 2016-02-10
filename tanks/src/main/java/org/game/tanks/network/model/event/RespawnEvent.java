package org.game.tanks.network.model.event;

import org.game.tanks.network.model.GameEvent;

public class RespawnEvent extends GameEvent {

  private static final long serialVersionUID = -4126883379693676031L;
  private long playerId;
  private int posX;
  private int posY;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

}
