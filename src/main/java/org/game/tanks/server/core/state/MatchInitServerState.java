package org.game.tanks.server.core.state;

import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.gameplay.GameplayManager;
import org.game.tanks.server.service.SyncStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
