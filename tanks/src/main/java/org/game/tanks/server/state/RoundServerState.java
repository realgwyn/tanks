package org.game.tanks.server.state;

import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundServerState extends ServerState {

  @Autowired
  private ProcessScheduler processScheduler;

  public RoundServerState() {
    super(ServerStateType.ROUND);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
  }

}
