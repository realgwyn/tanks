package org.game.tanks.server.state;

import java.util.List;
import java.util.stream.Collectors;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.server.service.MapService;
import org.game.tanks.server.service.PlayerConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

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
  PlayerConnectionService playerConnectionService;

  private Long startTime;
  private Long waitTime = 3000l;

  public InitializingMatchServerState() {
    super(ServerStateType.LOADING_MAP);
  }

  @Override
  public void onStateBegin() {

    List<Connection> playerConnections = collectPlayerConnections(ctx.getPlayers());
    ctx.reinitialize();
    mapService.loadNextMap();
    reconectPlayers(playerConnections);
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

  public List<Connection> collectPlayerConnections(List<PlayerServerModel> players) {
    return players.stream().map(player -> player.getConnection()).collect(Collectors.toList());
  }

  public void reconectPlayers(List<Connection> playerConnections) {
    for (Connection connection : playerConnections) {
      playerConnectionService.initializeNewPlayerConnection(connection);
    }
  }

}
