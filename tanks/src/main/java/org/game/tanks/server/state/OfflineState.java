package org.game.tanks.server.state;

import org.springframework.stereotype.Component;

@Component
public class OfflineState extends ServerState{

  @Override
  public void update() {
    // not accepting network data
  }

}
