package io.game.tanks.server.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.state.ServerStateType;
import io.game.tanks.server.cfg.ServerConfig;
import io.game.tanks.server.core.ServerController;
import io.game.tanks.server.core.ServerEngine;
import io.game.tanks.server.core.process.ProcessScheduler;
import io.game.tanks.server.core.process.SchedulerContext;
import io.game.tanks.server.gameplay.GameplayManager;

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
  ServerConfig config;
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
