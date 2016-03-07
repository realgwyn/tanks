package org.game.tanks.server.gameplay;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.process.SchedulerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameplayManager {

  @Autowired
  ServerContext serverContext;
  @Autowired
  SchedulerContext schedulerContext;

  private GameType gameType;

  public void reinitialize() {
    gameType = serverContext.getGameType();
    gameType.reinitialize();
  }

  public boolean matchTimePassed() {
    long currentTime = System.currentTimeMillis();
    return serverContext.getMatchEndTime() - currentTime < 0;
  }

  public boolean roundTimePassed() {
    long currentTime = System.currentTimeMillis();
    return serverContext.getRoundEndTime() - currentTime < 0;
  }

  public boolean roundObjectivesCompleted() {
    return gameType.roundObjectivesCompleted();
  }

  public void scoreRoundTimeout() {
    gameType.scoreRoundTimeout();
  }

  public void scoreRound() {
    gameType.scoreRound();
  }

}
