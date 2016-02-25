package org.game.tanks.network.model.command.admin;

import org.game.tanks.network.model.AdminCommand;

public class KickPlayer extends AdminCommand {

  private static final long serialVersionUID = 5454280950113353346L;
  private long playerId;
  private String reason;

  public long getPlayerId() {
    return playerId;
  }

  public KickPlayer setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public KickPlayer setReason(String reason) {
    this.reason = reason;
    return this;
  }

}