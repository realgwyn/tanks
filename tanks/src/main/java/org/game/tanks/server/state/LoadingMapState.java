package org.game.tanks.server.state;

import org.springframework.stereotype.Component;

@Component
public class LoadingMapState extends ServerState{

  @Override
  public void update() {
    // Loading map, waiting for players connections
  }

}
