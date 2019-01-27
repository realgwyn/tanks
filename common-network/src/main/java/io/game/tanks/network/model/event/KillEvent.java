package io.game.tanks.network.model.event;

import io.game.tanks.network.model.GameEvent;

public class KillEvent extends GameEvent {

  private static final long serialVersionUID = -9018523221378618934L;
  private int playerId;

  public KillEvent() {

  }

  public long getPlayerId() {
    return playerId;
  }

  public KillEvent setPlayerId(int playerId) {
    this.playerId = playerId;
    return this;
  }

  @Override
  public String toString() {
    return "KillEvent [playerId=" + playerId + "]";
  }

}
