package org.game.tanks.server.state;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.gameplay.GameplayManager;
import org.game.tanks.server.service.SyncStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundEndServerState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerController controller;
  @Autowired
  ServerContext serverCtx;
  @Autowired
  SyncStateService syncStateService;
  @Autowired
  RoundStartServerState roundStartState;
  @Autowired
  MatchEndServerState matchEndState;
  @Autowired
  ServerEngine engine;
  @Autowired
  GameplayManager gameplayManager;

  private ServerState nextState;

  private static final long TIME_UNTIL_NEXT_STATE = 5000;

  public RoundEndServerState() {
    super(ServerStateType.ROUND_END);
  }

  @Override
  public void onStateBegin() {
    // TODO: score team which won the round
    // TODO: send message which team won round
    //
    // serverCtx.getOutgoingCommands().add(
    // new ChangeState().setType(syncStateService.resolveClientState()));
    if (gameplayManager.matchTimePassed()) {
      nextState = matchEndState;
    } else {
      nextState = roundStartState;
    }

    syncStateService.syncClients(nextState.getType(), TIME_UNTIL_NEXT_STATE);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
    if (timePassed(TIME_UNTIL_NEXT_STATE)) {
      engine.setState(nextState);
    }
  }

  @Override
  public void onStateEnd() {
    //
  }

}