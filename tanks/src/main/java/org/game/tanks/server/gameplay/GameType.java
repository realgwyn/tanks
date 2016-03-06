package org.game.tanks.server.gameplay;

public abstract class GameType {

  public abstract void reinitialize();

  public abstract boolean roundObjectivesCompleted();

  public abstract void scoreRoundTimeout();

  public abstract void scoreRound();

}
