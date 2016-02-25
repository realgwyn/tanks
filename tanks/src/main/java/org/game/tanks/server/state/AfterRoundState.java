package org.game.tanks.server.state;

import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AfterRoundState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;

  public AfterRoundState() {
    super(ServerStateType.AFTER_ROUND);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
  }

}