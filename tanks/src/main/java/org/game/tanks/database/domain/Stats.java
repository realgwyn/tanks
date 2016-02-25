package org.game.tanks.database.domain;

public class Stats {

  private User user;
  private long totalGameSpent;
  private long kills;
  private long deaths;

  public User getUser() {
    return user;
  }

  public Stats setUser(User user) {
    this.user = user;
    return this;
  }

  public long getTotalGameSpent() {
    return totalGameSpent;
  }

  public Stats setTotalGameSpent(long totalGameSpent) {
    this.totalGameSpent = totalGameSpent;
    return this;
  }

  public long getKills() {
    return kills;
  }

  public Stats setKills(long kills) {
    this.kills = kills;
    return this;
  }

  public long getDeaths() {
    return deaths;
  }

  public Stats setDeaths(long deaths) {
    this.deaths = deaths;
    return this;
  }

}
