package io.tanks.server.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.state.ServerStateType;
import io.tanks.server.core.ServerEngine;
import io.tanks.server.core.process.ProcessScheduler;
import io.tanks.server.gameplay.GameplayManager;
import io.tanks.server.service.SyncStateService;

@Component
public class RoundServerState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  GameplayManager gameplayManager;
  @Autowired
  RoundEndServerState nextState;
  @Autowired
  SyncStateService syncStateService;
  @Autowired
  ServerEngine engine;

  public RoundServerState() {
    super(ServerStateType.ROUND);
  }

  @Override
  public void onStateBegin() {
  }

  @Override
  public void update() {
    processScheduler.runProcesses();

    if (gameplayManager.roundTimePassed()) {
      gameplayManager.scoreRoundTimeout();
      syncStateService.syncClients(nextState.getType(), 0);
      engine.setState(nextState);
    } else if (gameplayManager.roundObjectivesCompleted()) {
      gameplayManager.scoreRound();
      syncStateService.syncClients(nextState.getType(), 0);
      engine.setState(nextState);
    }
  }

  @Override
  public void onStateEnd() {
  }

}
