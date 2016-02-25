package org.game.tanks.database.domain;

public class BanHistory {

  private User player;
  private Long bannedTime;
  private Long bannedUntil;
  private String reason;
  private String comment;

  public User getPlayer() {
    return player;
  }

  public BanHistory setPlayer(User player) {
    this.player = player;
    return this;
  }

  public Long getBannedTime() {
    return bannedTime;
  }

  public BanHistory setBannedTime(Long bannedTime) {
    this.bannedTime = bannedTime;
    return this;
  }

  public Long getBannedUntil() {
    return bannedUntil;
  }

  public BanHistory setBannedUntil(Long bannedUntil) {
    this.bannedUntil = bannedUntil;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public BanHistory setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public BanHistory setComment(String comment) {
    this.comment = comment;
    return this;
  }

}
