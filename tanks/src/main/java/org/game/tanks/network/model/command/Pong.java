package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

public class Pong extends Command {

  private static final long serialVersionUID = 1949594791333702789L;
  private long id;
  private long playerId;
  private long sentTime;

  public long getPlayerId() {
    return playerId;
  }

  public Pong setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public long getSentTime() {
    return sentTime;
  }

  public Pong setSentTime(long sentTime) {
    this.sentTime = sentTime;
    return this;
  }

  public long getId() {
    return id;
  }

  public Pong setId(long id) {
    this.id = id;
    return this;
  }

}

