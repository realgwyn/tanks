package org.game.tanks.database.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stats {

  @Id
  private Long id;
  private long totalGameSpent;
  private long kills;
  private long deaths;

  public Long getId() {
    return id;
  }

  public Stats setId(Long id) {
    this.id = id;
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
