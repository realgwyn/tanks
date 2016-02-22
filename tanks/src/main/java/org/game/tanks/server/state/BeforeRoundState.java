package org.game.tanks.server.state;

import org.springframework.stereotype.Component;

/**
 * @author rafal.kojta
 */
@Component
public class BeforeRoundState extends ServerState {

  @Override
  public void update() {
    // freeze players + countdown
  }

}
