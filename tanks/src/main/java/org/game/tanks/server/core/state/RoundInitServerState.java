package org.game.tanks.server.core.state;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.gameplay.GameplayManager;
import org.game.tanks.server.service.SyncStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundInitServerState extends ServerState {

  public RoundInitServerState() {
    super(ServerStateType.ROUND_INIT);
  }

  private static final long TIME_UNTIL_NEXT_STATE = 5000;

  @Autowired
  ServerContext serverContext;
  @Autowired
  GameplayManager gameplayManager;
  @Autowired
  RoundStartServerState roundStartState;
  @Autowired
  ServerEngine engine;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  SyncStateService syncStateService;

  @Override
  public void onStateBegin() {
    syncStateService.syncClients(roundStartState.getType(), TIME_UNTIL_NEXT_STATE);
    serverContext.setNewRoundFlipFlag(!serverContext.getNewRoundFlipFlag());
    gameplayManager.initializeRound();
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
    if (timePassed(TIME_UNTIL_NEXT_STATE)) {
      engine.setState(roundStartState);
    }
  }

  @Override
  public void onStateEnd() {

  }

}
