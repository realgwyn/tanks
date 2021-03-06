package io.tanks.server.gameplay;

import java.util.List;

import io.tanks.server.model.PlayerServerModel;

public abstract class GameType {

  public abstract void reinitialize();

  public abstract boolean roundObjectivesCompleted();

  public abstract void scoreRoundTimeout();

  public abstract void scoreRound();

  public abstract void initializePlayersProperties(List<PlayerServerModel> players);

  public abstract boolean playersAreReadyForNewMatch();

}
