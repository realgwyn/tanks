package org.game.tanks.network.model.command;

import java.util.List;

import org.game.tanks.network.model.TCPMessage;

public class GameInitData extends TCPMessage {

  private int mapTimeLeft;
  private int roundTimeLeft;
  private List<PlayerInfo> playersStats;
  private MapInfoData currentMap;
  private MapInfoData nextMap;

}
