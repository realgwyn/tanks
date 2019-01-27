package io.tanks.server.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.state.ServerStateType;
import io.tanks.server.core.ServerEngine;
import io.tanks.server.gameplay.GameplayManager;
import io.tanks.server.service.SyncStateService;

@Component
public class MatchInitServerState extends ServerState {

  @Autowired
  ServerEngine engine;
  @Autowired
  WaitingForPlayersServerState nextState;
  @Autowired
  SyncStateService syncStateService;
  @Autowired
  GameplayManager gameplayManager;

  private static final long TIME_UNTIL_NEXT_STATE = 1000;

  public MatchInitServerState() {
    super(ServerStateType.MATCH_INIT);
  }

  @Override
  public void onStateBegin() {
    gameplayManager.initializeMatch();
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
