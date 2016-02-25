package org.game.tanks.database.domain;

public class User {

  private Long userId;
  private String username;
  private String hash;
  private String email;
  private Boolean banned;

  public Long getUserId() {
    return userId;
  }

  public User setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getHash() {
    return hash;
  }

  public User setHash(String hash) {
    this.hash = hash;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public Boolean getBanned() {
    return banned;
  }

  public User setBanned(Boolean banned) {
    this.banned = banned;
    return this;
  }

}
