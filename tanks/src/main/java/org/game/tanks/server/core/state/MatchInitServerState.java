package org.game.tanks.server.core.state;

import javax.annotation.PostConstruct;

import org.game.tanks.server.core.PlayerConnectionThread;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.gameplay.GameplayManager;
import org.game.tanks.server.service.MapService;
import org.game.tanks.server.service.SyncStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchInitServerState extends ServerState {

  @Autowired
  ServerEngine engine;
  @Autowired
  ServerContext serverContext;
  @Autowired
  MapService mapService;
  @Autowired
  WaitingForPlayersServerState nextState;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  PlayerConnectionThread playerConnectionThread;
  @Autowired
  SyncStateService syncStateService;
  @Autowired
  GameplayManager gameplayManager;

  private static final long TIME_UNTIL_NEXT_STATE = 1000;

  private long MATCH_DURATION_MILLIS;

  public MatchInitServerState() {
    super(ServerStateType.MATCH_INIT);
  }

  @PostConstruct
  public void init() {
    MATCH_DURATION_MILLIS = serverContext.getMatchDurationMinutes() * 1000 * 60;
  }

  @Override
  public void onStateBegin() {
    processScheduler.reinitialize();
    serverContext.reinitialize();
    serverContext.setMatchEndTime(System.currentTimeMillis() + MATCH_DURATION_MILLIS);
    mapService.loadNextMap();

    gameplayManager.reinitialize();
    playerConnectionThread.reconnectPlayers();
    syncStateService.syncClients(nextState.getType(), TIME_UNTIL_NEXT_STATE);
  }

  @Override
  public void update() {
    if (timePassed(TIME_UNTIL_NEXT_STATE)) {
      engine.setState(nextState);
    }
  }

  @Override
  public void onStateEnd() {

  }

}
