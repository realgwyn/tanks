package org.game.tanks.network.model.command;

import java.util.List;

import org.game.tanks.network.model.Command;

public class GameInitData extends Command {

  private static final long serialVersionUID = -5388071508719254864L;
  private long matchEndTime;
  private long roundEndTime;
  private List<PlayerInfo> playersInfo;
  private MapInfoData currentMap;
  private MapInfoData nextMap;

  public GameInitData() {

  }

  public List<PlayerInfo> getPlayersInfo() {
    return playersInfo;
  }

  public GameInitData setPlayersInfo(List<PlayerInfo> playersStats) {
    this.playersInfo = playersStats;
    return this;
  }

  public MapInfoData getCurrentMap() {
    return currentMap;
  }

  public GameInitData setCurrentMap(MapInfoData currentMap) {
    this.currentMap = currentMap;
    return this;
  }

  public MapInfoData getNextMap() {
    return nextMap;
  }

  public GameInitData setNextMap(MapInfoData nextMap) {
    this.nextMap = nextMap;
    return this;
  }

  public long getMatchEndTime() {
    return matchEndTime;
  }

  public GameInitData setMatchEndTime(long matchEndTime) {
    this.matchEndTime = matchEndTime;
    return this;
  }

  public long getRoundEndTime() {
    return roundEndTime;
  }

  public GameInitData setRoundEndTime(long roundEndTime) {
    this.roundEndTime = roundEndTime;
    return this;
  }

  @Override
  public String toString() {
    return "GameInitData [matchEndTime=" + matchEndTime + ", roundEndTime=" + roundEndTime + ", players=" + playersInfo.size()
        + ", currentMap=" + currentMap + ", nextMap=" + nextMap + "]";
  }

}
