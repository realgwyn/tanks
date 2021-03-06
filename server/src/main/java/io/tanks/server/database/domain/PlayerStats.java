package io.tanks.server.database.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlayerStats {

  @Id
  private Long id;
  private Integer totalTimeSpent = 0;
  private Integer gamesPlayed = 0;
  private Integer kills = 0;
  private Integer deaths = 0;

  public Long getId() {
    return id;
  }

  public Integer getTotalTimeSpent() {
    return totalTimeSpent;
  }

  public PlayerStats setTotalTimeSpent(Integer totalTimeSpent) {
    this.totalTimeSpent = totalTimeSpent;
    return this;
  }

  public Integer getGamesPlayed() {
    return gamesPlayed;
  }

  public PlayerStats setGamesPlayed(Integer gamesPlayed) {
    this.gamesPlayed = gamesPlayed;
    return this;
  }

  public Integer getKills() {
    return kills;
  }

  public PlayerStats setKills(Integer kills) {
    this.kills = kills;
    return this;
  }

  public Integer getDeaths() {
    return deaths;
  }

  public PlayerStats setDeaths(Integer deaths) {
    this.deaths = deaths;
    return this;
  }

}
