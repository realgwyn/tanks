package org.game.tanks.server.state;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitingForPlayersServerState extends ServerState {

  @Autowired
  ServerContext ctx;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerEngine engine;
  @Autowired
  BeforeRoundServerState beforeRoundState;
  @Autowired
  Config config;
  @Autowired
  ServerController controller;

  public WaitingForPlayersServerState() {
    super(ServerStateType.WAITING_FOR_PLAYERS);
  }

  @PostConstruct
  public void init() {

  }

  @Override
  public void onStateBegin() {

  }

  @Override
  public void update() {
    processScheduler.runProcesses();

    if (ctx.getPlayers().size() > 1) {
      engine.setState(beforeRoundState);
    }
  }

  @Override
  public void onStateEnd() {
    controller.initNewMatch();
  }

}
