package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Disconnect extends Command {

  private static final long serialVersionUID = -4729948455525322010L;
  private int playerId;

  public Disconnect() {

  }

  public int getPlayerId() {
    return playerId;
  }

  public Disconnect setPlayerId(int playerId) {
    this.playerId = playerId;
    return this;
  }

}
