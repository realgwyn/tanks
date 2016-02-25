package org.game.tanks.network.model.event;

import org.game.tanks.network.model.GameEvent;

public class MoveEvent extends GameEvent {

  private static final long serialVersionUID = -1866017124586969518L;
  private int newX;
  private int newY;

  public int getNewX() {
    return newX;
  }

  public void setNewX(int newX) {
    this.newX = newX;
  }

  public int getNewY() {
    return newY;
  }

  public void setNewY(int newY) {
    this.newY = newY;
  }

}
