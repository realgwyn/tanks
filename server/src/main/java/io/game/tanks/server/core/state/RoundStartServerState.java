package io.game.tanks.server.core.state;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.state.ServerStateType;
import io.game.tanks.server.cfg.ServerConfig;
import io.game.tanks.server.core.ServerEngine;
import io.game.tanks.server.core.process.ProcessScheduler;
import io.game.tanks.server.gameplay.GameplayManager;

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
  ServerConfig cfg;
  @Autowired
  GameplayManager gameplayManager;

  private long TIME_UNTIL_NEXT_STATE;

  @PostConstruct
  public void init() {
    TIME_UNTIL_NEXT_STATE = cfg.getPropertyInt(ServerConfig.SERVER_ROUND_START_FREEZE_TIME_SECONDS, 1);
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
