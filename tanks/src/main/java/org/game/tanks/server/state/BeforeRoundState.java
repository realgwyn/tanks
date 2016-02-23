package org.game.tanks.server.state;

import org.game.tanks.server.core.GameEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafal.kojta
 */
@Component
public class BeforeRoundState extends ServerState {

  @Autowired
  private GameEventHandler gameEventHandler;

  @Override
  public void update() {
    gameEventHandler.processGameEvents();
    // ...
    gameEventHandler.sendOutGameEvents();
  }

}
