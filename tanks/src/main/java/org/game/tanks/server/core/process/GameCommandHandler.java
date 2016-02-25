package org.game.tanks.server.core.process;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerNetworkAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processes GameCommands
 */
@Component
public class GameCommandHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  @Override
  public void runProcess() {

  }

}
