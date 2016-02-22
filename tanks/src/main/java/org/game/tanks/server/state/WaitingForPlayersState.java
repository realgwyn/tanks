package org.game.tanks.server.state;

import org.game.tanks.server.core.GameEventHandler;
import org.game.tanks.server.core.PlayerConnectionService;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitingForPlayersState extends ServerState {

  @Autowired
  ServerContext ctx;
  @Autowired
  GameEventHandler gameEventHandler;
  @Autowired
  PlayerConnectionService playerConnectionManager;
  @Autowired
  ServerEngine engine;
  @Autowired
  BeforeRoundState beforeRoundState;

  @Override
  public void update() {
    // display info that points will not start until there will be more than one player in the game
    gameEventHandler.processGameEvents();
    playerConnectionManager.processPlayerConnections();

    if (ctx.getPlayers().size() > 1) {
      engine.setState(beforeRoundState);
    }
  }

}
