package org.game.tanks.server.state;

import javax.annotation.PostConstruct;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafal.kojta
 */
@Component
public class RoundStartServerState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerContext serverCtx;
  @Autowired
  RoundServerState nextState;
  @Autowired
  ServerEngine engine;

  private static final long TIME_UNTIL_NEXT_STATE = 10000;

  private long ROUND_DURATION_MILLIS;

  @PostConstruct
  public void init() {
    ROUND_DURATION_MILLIS = serverCtx.getRoundDurationMinutes() * 1000 * 60;
  }

  public RoundStartServerState() {
    super(ServerStateType.ROUND_START);
  }

  @Override
  public void onStateBegin() {
    serverCtx.setRoundEndTime(System.currentTimeMillis() + ROUND_DURATION_MILLIS);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
    // Player movements are blocked at the beginning of the round to enable buying items
    if (timePassed(TIME_UNTIL_NEXT_STATE)) {
      engine.setState(nextState);
    }
  }

  @Override
  public void onStateEnd() {

  }

}
