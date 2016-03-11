package org.game.tanks.server.core.state;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.gameplay.GameplayManager;
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
  RoundServerState nextState;
  @Autowired
  ServerEngine engine;
  @Autowired
  Config cfg;
  @Autowired
  GameplayManager gameplayManager;

  private long TIME_UNTIL_NEXT_STATE;

  @PostConstruct
  public void init() {
    TIME_UNTIL_NEXT_STATE = cfg.getPropertyInt(Config.SERVER_ROUND_START_FREEZE_TIME_SECONDS, 1);
  }

  public RoundStartServerState() {
    super(ServerStateType.ROUND_START);
  }

  @Override
  public void onStateBegin() {
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
