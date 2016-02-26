package org.game.tanks.client.core;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.game.tanks.model.PlayerModel;
import org.springframework.stereotype.Component;

@Component
public class GameContext {

  private long serverTimeOffset;
  ConcurrentLinkedQueue<PlayerModel> players;
  private String playerId;
  private String playerName;
  private int playerPosX;
  private int playerPosY;

  public int getPlayerPosX() {
    return playerPosX;
  }

  public void setPlayerPosX(int playerPosX) {
    this.playerPosX = playerPosX;
  }

  public int getPlayerPosY() {
    return playerPosY;
  }

  public void setPlayerPosY(int playerPosY) {
    this.playerPosY = playerPosY;
  }

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public ConcurrentLinkedQueue<PlayerModel> getPlayers() {
    return players;
  }

  public void setPlayers(ConcurrentLinkedQueue<PlayerModel> players) {
    this.players = players;
  }

  public long getServerTimeOffset() {
    return serverTimeOffset;
  }

  public void setServerTimeOffset(long serverTimeOffset) {
    this.serverTimeOffset = serverTimeOffset;
  }

}
