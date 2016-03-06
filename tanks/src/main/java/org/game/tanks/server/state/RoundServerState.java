package org.game.tanks.server.state;

import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.gameplay.GameplayManager;
import org.game.tanks.server.service.SyncStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    } else if (gameplayManager.roundObjectivesCompleted()) {
      gameplayManager.scoreRound();
      syncStateService.syncClients(nextState.getType(), 0);
    }
  }

  @Override
  public void onStateEnd() {
  }

}
