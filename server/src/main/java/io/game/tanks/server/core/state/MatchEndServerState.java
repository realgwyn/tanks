package io.game.tanks.server.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.state.ServerStateType;
import io.game.tanks.server.core.ServerContext;
import io.game.tanks.server.core.ServerEngine;
import io.game.tanks.server.service.SyncStateService;

@Component
public class MatchEndServerState extends ServerState {

  @Autowired
  ServerEngine engine;
  @Autowired
  MatchInitServerState nextState;
  @Autowired
  ServerContext serverCtx;
  @Autowired
  SyncStateService syncStateService;

  private static final long TIME_UNTIL_NEXT_STATE = 8000;

  public MatchEndServerState() {
    super(ServerStateType.MATCH_END);
  }

  @Override
  public void onStateBegin() {
    // TODO: calculate and show final scores
    // TODO: save scores to DB

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
