package io.tanks.server.core.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.model.AdminCommand;
import io.tanks.common.network.model.command.Disconnect;
import io.tanks.common.network.model.command.admin.BanPlayer;
import io.tanks.common.network.model.command.admin.ChangeMap;
import io.tanks.common.network.model.command.admin.ChangeNextMap;
import io.tanks.common.network.model.command.admin.KickPlayer;
import io.tanks.common.network.model.command.admin.RestartMatch;
import io.tanks.common.network.model.game.MapModel;
import io.tanks.server.core.PlayerConnectionThread;
import io.tanks.server.core.ServerContext;
import io.tanks.server.core.ServerEngine;
import io.tanks.server.core.ServerEventBus;
import io.tanks.server.core.state.MatchInitServerState;
import io.tanks.server.core.task.TaskManager;
import io.tanks.server.service.MapService;

@Component
public class AdminCommandProcessor extends ScheduledProcess {

  private final static Logger logger = Logger.getLogger(AdminCommandProcessor.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerEventBus bus;
  @Autowired
  TaskManager taskManager;
  @Autowired
  ServerEngine engine;
  @Autowired
  MatchInitServerState matchInitState;
  @Autowired
  MapService mapService;
  @Autowired
  PlayerConnectionThread playerConnectionThread;
  @Autowired
  ProcessSchedulerContext schedulerContext;

  @Override
  public void execute() {
    while (!bus.getIncomingAdminCommands().isEmpty()) {
      AdminCommand cmd = bus.getIncomingAdminCommands().poll();
      if (cmd instanceof BanPlayer) {
        processBanPlayerCommand((BanPlayer) cmd);
      } else if (cmd instanceof ChangeMap) {
        processChangeMapCommand((ChangeMap) cmd);
      } else if (cmd instanceof ChangeNextMap) {
        processChangeNextMapCommand((ChangeNextMap) cmd);
      } else if (cmd instanceof KickPlayer) {
        processKickPlayerCommand((KickPlayer) cmd);
      } else if (cmd instanceof RestartMatch) {
        processRestartMatchCommand((RestartMatch) cmd);
      } else {
        throw new UnsupportedOperationException("Unsupported type of command: " + cmd.getClass().getSimpleName());
      }
    }
  }

  private void processBanPlayerCommand(BanPlayer cmd) {
    logger.debug("Banning player id:" + cmd.getPlayerId());

    bus.getOutgoingCommands().add(new Disconnect().setPlayerId(cmd.getPlayerId()));

    // TODO: implement banning by playerId (not connectionId!)
    // BanHistory entity = new BanHistory();
    // taskManager.createDbTask(entity, DatabaseAction.CREATE);
  }

  private void processKickPlayerCommand(KickPlayer cmd) {
    logger.debug("Kicking player id:" + cmd.getPlayerId());
    bus.getOutgoingCommands().add(new Disconnect().setPlayerId(cmd.getPlayerId()));
  }

  private void processChangeMapCommand(ChangeMap cmd) {
    MapModel map = mapService.loadMap(mapService.getNextMapName());
    schedulerContext.setCurrentMap(map);
    playerConnectionThread.setCurrentMap(map);
    engine.setState(matchInitState);
  }

  private void processChangeNextMapCommand(ChangeNextMap cmd) {
    throw new UnsupportedOperationException("Not implemented yet.");
    // ctx.setNextMap(mapService.loadMap(cmd.getMapName()));
  }

  private void processRestartMatchCommand(RestartMatch cmd) {
    engine.setState(matchInitState);
  }

}
