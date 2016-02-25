package org.game.tanks.database.domain;

public class BannedIp {

  private User player;
  private String ipAddress;
  private Long banTimeLenght;

  public User getPlayer() {
    return player;
  }

  public BannedIp setPlayer(User player) {
    this.player = player;
    return this;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public BannedIp setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  public Long getBanTimeLenght() {
    return banTimeLenght;
  }

  public BannedIp setBanTimeLenght(Long banTimeLenght) {
    this.banTimeLenght = banTimeLenght;
    return this;
  }

}
