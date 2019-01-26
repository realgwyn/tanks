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

  public WaitingForPlayersServerState() {
    super(ServerStateType.WAITING_FOR_PLAYERS);
  }

  @Override
  public void onStateBegin() {
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
    if (gameplayManager.playersAreReadyForNewMatch()) {
      engine.setState(nextState);
    }
  }

  @Override
  public void onStateEnd() {
  }

}
