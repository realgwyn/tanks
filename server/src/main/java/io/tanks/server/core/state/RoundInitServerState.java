package io.tanks.server.core.state;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.state.ServerStateType;
import io.tanks.server.core.ServerContext;
import io.tanks.server.core.ServerEngine;
import io.tanks.server.core.process.ProcessScheduler;
import io.tanks.server.gameplay.GameplayManager;
import io.tanks.server.service.SyncStateService;

@Component
public class RoundInitServerState extends ServerState {

  private final static Logger logger = Logger.getLogger(RoundInitServerState.class);

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
    logger.debug("Initializing new round");
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
