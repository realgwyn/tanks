package io.game.tanks.server.database.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class BanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private User player;

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date bannedTime;

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date bannedUntil;

  private String reason;

  private String comment;

  public Long getId() {
    return id;
  }

  public BanHistory setId(Long id) {
    this.id = id;
    return this;
  }

  public User getPlayer() {
    return player;
  }

  public BanHistory setPlayer(User player) {
    this.player = player;
    return this;
  }

  public Date getBannedTime() {
    return bannedTime;
  }

  public BanHistory setBannedTime(Date bannedTime) {
    this.bannedTime = bannedTime;
    return this;
  }

  public Date getBannedUntil() {
    return bannedUntil;
  }

  public BanHistory setBannedUntil(Date bannedUntil) {
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
