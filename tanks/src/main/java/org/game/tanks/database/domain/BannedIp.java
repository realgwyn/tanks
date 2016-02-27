package org.game.tanks.database.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BannedIp {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private User player;

  private Date bannedUntil;
  private String ipAddress;

  public Long getId() {
    return id;
  }

  public BannedIp setId(Long id) {
    this.id = id;
    return this;
  }

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

  public Date getBannedUntil() {
    return bannedUntil;
  }

  public BannedIp setBannedUntil(Date bannedUntil) {
    this.bannedUntil = bannedUntil;
    return this;
  }

}
