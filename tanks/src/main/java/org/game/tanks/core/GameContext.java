package org.game.tanks.core;

import org.springframework.stereotype.Component;

@Component
public class GameContext {
  
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
}
