package io.tanks.server.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.state.ServerStateType;
import io.tanks.server.cfg.ServerConfig;
import io.tanks.server.core.ServerController;
import io.tanks.server.core.ServerEngine;
import io.tanks.server.core.process.ProcessScheduler;
import io.tanks.server.core.process.SchedulerContext;
import io.tanks.server.gameplay.GameplayManager;

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
