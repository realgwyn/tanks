package org.game.tanks.network.model.event;

import org.game.tanks.network.model.GameEvent;

public class KillEvent extends GameEvent {

  private static final long serialVersionUID = -9018523221378618934L;
  private int playerId;

  public long getPlayerId() {
    return playerId;
  }

  public KillEvent setPlayerId(int playerId) {
    this.playerId = playerId;
    return this;
  }

}
