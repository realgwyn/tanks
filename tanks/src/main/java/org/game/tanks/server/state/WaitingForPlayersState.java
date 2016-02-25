package org.game.tanks.server.state;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitingForPlayersState extends ServerState {

  @Autowired
  ServerContext ctx;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerEngine engine;
  @Autowired
  BeforeRoundState beforeRoundState;

  public WaitingForPlayersState() {
    super(ServerStateType.WAITING_FOR_PLAYERS);
  }

  @Override
  public void update() {
    processScheduler.runProcesses();

    // display info that points will not start until there will be more than one player in the game

    if (ctx.getPlayers().size() > 1) {
      engine.setState(beforeRoundState);
    }
  }

}
