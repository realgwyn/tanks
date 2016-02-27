package org.game.tanks.network.model.command;

import java.util.List;

import org.game.tanks.network.model.TCPMessage;

public class GameInitData extends TCPMessage {

  private static final long serialVersionUID = -5388071508719254864L;
  private int mapTimeLeft;
  private int roundTimeLeft;
  private List<PlayerInfo> playersStats;
  private MapInfoData currentMap;
  private MapInfoData nextMap;

  public int getMapTimeLeft() {
    return mapTimeLeft;
  }

  public GameInitData setMapTimeLeft(int mapTimeLeft) {
    this.mapTimeLeft = mapTimeLeft;
    return this;
  }

  public int getRoundTimeLeft() {
    return roundTimeLeft;
  }

  public GameInitData setRoundTimeLeft(int roundTimeLeft) {
    this.roundTimeLeft = roundTimeLeft;
    return this;
  }

  public List<PlayerInfo> getPlayersStats() {
    return playersStats;
  }

  public GameInitData setPlayersStats(List<PlayerInfo> playersStats) {
    this.playersStats = playersStats;
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

}
