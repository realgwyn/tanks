package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Ping extends Command {

  private static final long serialVersionUID = 6758233706960759962L;
  private long playerId;
  private long sentTime;

  public long getPlayerId() {
    return playerId;
  }

  public Ping setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public long getSentTime() {
    return sentTime;
  }

  public Ping setSentTime(long sentTime) {
    this.sentTime = sentTime;
    return this;
  }

  @Override
  public String toString() {
    return "Ping [playerId=" + playerId + ", sentTime=" + sentTime + "]";
  }

}
