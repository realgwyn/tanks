package org.game.tanks.network.model.command.admin;

import org.game.tanks.network.model.AdminCommand;

public class BanPlayer extends AdminCommand{

  private static final long serialVersionUID = -3767574307222712576L;

  public enum BanReason {CHEATING, VERBAL_ABUSEMENT, ASSHOLE, OTHER}
  
  private long playerId;
  private long banTimeLenght;
  private BanReason reason;
  private String comment;

  public long getPlayerId() {
    return playerId;
  }

  public BanPlayer setPlayerId(long playerId) {
    this.playerId = playerId;
    return this;
  }

  public long getBanTimeLenght() {
    return banTimeLenght;
  }

  public BanPlayer setBanTimeLenght(long banTimeLenght) {
    this.banTimeLenght = banTimeLenght;
    return this;
  }

  public BanReason getReason() {
    return reason;
  }

  public BanPlayer setReason(BanReason reason) {
    this.reason = reason;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public BanPlayer setComment(String comment) {
    this.comment = comment;
    return this;
  }
  
}
