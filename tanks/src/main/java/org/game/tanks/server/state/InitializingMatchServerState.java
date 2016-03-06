package org.game.tanks.server.state;

import org.game.tanks.server.core.PlayerConnectionThread;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializingMatchServerState extends ServerState {

  @Autowired
  ServerEngine engine;
  @Autowired
  ServerContext ctx;
  @Autowired
  MapService mapService;
  @Autowired
  WaitingForPlayersServerState waitingForPlayersState;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  PlayerConnectionThread playerConnectionThread;

  private Long startTime;
  private Long waitTime = 3000l;

  public InitializingMatchServerState() {
    super(ServerStateType.LOADING_MAP);
  }

  @Override
  public void onStateBegin() {
    processScheduler.reinitialize();
    ctx.reinitialize();
    mapService.loadNextMap();
    playerConnectionThread.reconnectPlayers();
  }

  @Override
  public void update() {
    processScheduler.runProcesses();
    if (startTime == null) {
      startTime = System.currentTimeMillis();
    }

    if (startTime + waitTime >= System.currentTimeMillis()) {
      engine.setState(waitingForPlayersState);
    }
  }

}
