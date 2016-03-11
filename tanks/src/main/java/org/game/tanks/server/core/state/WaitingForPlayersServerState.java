package org.game.tanks.server.core.state;

import org.game.tanks.cfg.Config;
import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.core.process.SchedulerContext;
import org.game.tanks.server.gameplay.GameplayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitingForPlayersServerState extends ServerState {

  @Autowired
  SchedulerContext schedulerCtx;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerEngine engine;
  @Autowired
  RoundInitServerState nextState;
  @Autowired
  Config config;
  @Autowired
  ServerController controller;
  @Autowired
  GameplayManager gameplayManager;

  private long TIME_BEFORE_NEXT_STATE = 2000;

  public WaitingForPlayersServerState() {
    super(ServerStateType.WAITING_FOR_PLAYERS);
  }

  @Override
  public void onStateBegin() {
  }

  @Override
  public void update() {
    processScheduler.runProcesses();

    // Wait until there are at least 2 players in the game
    // XXX: uncomment this
    if (gameplayManager.playersAreReadyForNewMatch()) {
      engine.setState(nextState);
    }

    // XXX:
    // engine.setState(nextState);
  }

  @Override
  public void onStateEnd() {
  }

}
