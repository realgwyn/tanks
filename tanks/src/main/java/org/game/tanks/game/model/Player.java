package org.game.tanks.game.model;

public class Player {

  public static enum PlayerState {
    DISCONNECTED, ALIVE, DEAD, UNDEFINED
  }

  private long playerId;
  private String playerName;
  private int posX;
  private int posY;
  private float health;
  private PlayerState state;

  public String getPlayerName() {
    return playerName;
  }

  public Player setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public int getPosX() {
    return posX;
  }

  public Player setPosX(int posX) {
    this.posX = posX;
    return this;
  }

  public int getPosY() {
    return posY;
  }

  public Player setPosY(int posY) {
    this.posY = posY;
    return this;
  }

  public PlayerState getState() {
    return state;
  }

  public Player setState(PlayerState state) {
    this.state = state;
    return this;
  }

  public float getHealth() {
    return health;
  }

  public Player setHealth(float health) {
    this.health = health;
    return this;
  }

}
