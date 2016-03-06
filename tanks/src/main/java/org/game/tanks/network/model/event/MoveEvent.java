package org.game.tanks.network.model.event;

import org.game.tanks.network.model.GameEvent;

public class MoveEvent extends GameEvent {

  private static final long serialVersionUID = -1866017124586969518L;
  private int newX;
  private int newY;

  public int getNewX() {
    return newX;
  }

  public MoveEvent setNewX(int newX) {
    this.newX = newX;
    return this;
  }

  public int getNewY() {
    return newY;
  }

  public MoveEvent setNewY(int newY) {
    this.newY = newY;
    return this;
  }

}
