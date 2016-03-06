package org.game.tanks.server.state;

import org.game.tanks.cfg.Config;
import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.core.process.SchedulerContext;
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
  RoundStartServerState roundStartState;
  @Autowired
  Config config;
  @Autowired
  ServerController controller;

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
    if (schedulerCtx.getPlayers().size() > 1) {
      engine.setState(roundStartState);
    }
  }

  @Override
  public void onStateEnd() {
  }

}
