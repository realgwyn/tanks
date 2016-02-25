package org.game.tanks.network.model;

public class AdminCommand extends TCPMessage {

  private static final long serialVersionUID = 6171368539631728601L;
  private String username;
  private String hash;

  public String getUsername() {
    return username;
  }

  public AdminCommand setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getHash() {
    return hash;
  }

  public AdminCommand setHash(String hash) {
    this.hash = hash;
    return this;
  }

}
