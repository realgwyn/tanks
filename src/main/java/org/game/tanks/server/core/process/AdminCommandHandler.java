package org.game.tanks.server.core.process;

import org.apache.log4j.Logger;
import org.game.tanks.model.MapModel;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.admin.BanPlayer;
import org.game.tanks.network.model.command.admin.ChangeMap;
import org.game.tanks.network.model.command.admin.ChangeNextMap;
import org.game.tanks.network.model.command.admin.KickPlayer;
import org.game.tanks.network.model.command.admin.RestartMatch;
import org.game.tanks.network.model.command.admin.SystemCommand;
import org.game.tanks.server.core.PlayerConnectionThread;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.ServerEventBus;
import org.game.tanks.server.core.state.MatchInitServerState;
import org.game.tanks.server.core.task.TaskManager;
import org.game.tanks.server.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminCommandHandler extends ScheduledProcess {

  private final static Logger logger = Logger.getLogger(AdminCommandHandler.class);

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
  SchedulerContext schedulerContext;

  @Override
  public void runProcess() {
    processAdminCommands();
  }

  private void processAdminCommands() {
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
      } else if (cmd instanceof SystemCommand) {
        processSystemCommand((SystemCommand) cmd);
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

  private void processSystemCommand(SystemCommand cmd) {
    // TODO
    // save property to cfg file
    // If property change requires server restart, send msg to admin informing about this
  }

}
