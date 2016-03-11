package org.game.tanks.server.gameplay;

import javax.annotation.PostConstruct;

import org.game.tanks.model.MapModel;
import org.game.tanks.server.core.PlayerConnectionThread;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.core.process.SchedulerContext;
import org.game.tanks.server.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameplayManager {

  @Autowired
  ServerContext serverContext;
  @Autowired
  SchedulerContext schedulerContext;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  MapService mapService;
  @Autowired
  PlayerConnectionThread playerConnectionThread;

  private GameType gameType;

  private long MATCH_DURATION_MILLIS;
  private long ROUND_DURATION_MILLIS;

  @PostConstruct
  public void init() {
    MATCH_DURATION_MILLIS = serverContext.getMatchDurationMillis();
    ROUND_DURATION_MILLIS = serverContext.getRoundDuationMillis();
  }

  public void initializeMatch() {
    processScheduler.reinitialize();
    serverContext.reinitialize();
    serverContext.setMatchEndTime(System.currentTimeMillis() + MATCH_DURATION_MILLIS);
    gameType = serverContext.getGameType();
    MapModel map = mapService.loadMap(mapService.getNextMapName());
    schedulerContext.setCurrentMap(map);
    playerConnectionThread.setCurrentMap(map);
    playerConnectionThread.reconnectPlayers();
  }

  public void initializeRound() {
    serverContext.setRoundEndTime(System.currentTimeMillis() + ROUND_DURATION_MILLIS);
    gameType.reinitialize();
    gameType.initializePlayersProperties(schedulerContext.getPlayers());
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

  public boolean playersAreReadyForNewMatch() {
    return gameType.playersAreReadyForNewMatch();
  }

}
