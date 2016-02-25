package org.game.tanks.server.state;

import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafal.kojta
 */
@Component
public class BeforeRoundState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;

  public BeforeRoundState() {
    super(ServerStateType.BEFORE_ROUND);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
  }

}
