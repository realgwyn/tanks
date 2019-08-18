package io.tanks.common.network.model.command.admin;

import io.tanks.common.network.model.AdminCommand;

public class BanPlayer extends AdminCommand {

  private static final long serialVersionUID = -3767574307222712576L;

  public BanPlayer() {

  }

  private int playerId;
  private long banTimeMinutes;
  private String reason;

  public int getPlayerId() {
    return playerId;
  }

  public BanPlayer setPlayerId(int playerId) {
    this.playerId = playerId;
    return this;
  }

  public long getBanTimeLenght() {
    return banTimeMinutes;
  }

  public BanPlayer setBanTimeMinutes(long banTimeMinutes) {
    this.banTimeMinutes = banTimeMinutes;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public BanPlayer setReason(String reason) {
    this.reason = reason;
    return this;
  }

}
