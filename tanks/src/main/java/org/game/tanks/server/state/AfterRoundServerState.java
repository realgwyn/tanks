package org.game.tanks.server.state;

import org.game.tanks.server.core.ServerController;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AfterRoundServerState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerController controller;

  public AfterRoundServerState() {
    super(ServerStateType.AFTER_ROUND);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
  }

  @Override
  public void onStateEnd() {
    controller.initNewRound();
  }

}