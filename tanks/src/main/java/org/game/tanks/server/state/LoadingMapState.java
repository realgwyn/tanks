package org.game.tanks.server.state;

import org.game.tanks.server.core.PlayerConnectionService;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.utils.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingMapState extends ServerState {

  @Autowired
  ServerEngine engine;
  @Autowired
  ServerContext context;
  @Autowired
  MapService mapService;
  @Autowired
  WaitingForPlayersState waitingForPlayersState;
  @Autowired
  PlayerConnectionService playerConnectionManager;

  private Long startTime;
  private Long waitTime = 3000l;

  @Override
  public void onStateBegin() {
    mapService.loadNextMap();
  }

  @Override
  public void update() {
    if (startTime == null) {
      startTime = System.currentTimeMillis();
    }

    if (startTime + waitTime >= System.currentTimeMillis()) {
      engine.setState(waitingForPlayersState);
    }

    playerConnectionManager.processPlayerConnections();
  }

}
