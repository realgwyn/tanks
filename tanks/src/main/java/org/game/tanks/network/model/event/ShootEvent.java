package org.game.tanks.network.model.event;

import org.game.tanks.network.model.GameEvent;

public class ShootEvent extends GameEvent {

  private static final long serialVersionUID = 1487022198642214486L;
  private long playerId;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

}
