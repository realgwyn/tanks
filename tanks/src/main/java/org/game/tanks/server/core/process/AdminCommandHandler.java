package org.game.tanks.server.core.process;

import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.command.admin.BanPlayer;
import org.game.tanks.network.model.command.admin.ChangeMap;
import org.game.tanks.network.model.command.admin.ChangeNextMap;
import org.game.tanks.network.model.command.admin.KickPlayer;
import org.game.tanks.network.model.command.admin.RestartMap;
import org.game.tanks.network.model.command.admin.SystemCommand;
import org.game.tanks.server.core.ServerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO XXX: those commands should be invoked in ServerController, not in time critical loop
@Component
public class AdminCommandHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;

  @Override
  public void runProcess() {
    processAdminCommands();
  }

  private void processAdminCommands() {
    while (!ctx.getIncomingAdminCommands().isEmpty()) {
      AdminCommand cmd = ctx.getIncomingAdminCommands().poll();
      if (cmd instanceof BanPlayer) {
        processPanPlayerCommand((BanPlayer) cmd);
      } else if (cmd instanceof ChangeMap) {
        processChangeMapCommand((ChangeMap) cmd);
      } else if (cmd instanceof ChangeNextMap) {
        processChangeNextMapCommand((ChangeNextMap) cmd);
      } else if (cmd instanceof KickPlayer) {
        processKickPlayerCommand((KickPlayer) cmd);
      } else if (cmd instanceof RestartMap) {
        processRestartMapCommand((RestartMap) cmd);
      } else if (cmd instanceof SystemCommand) {
        processSystemCommand((SystemCommand) cmd);
      } else {
        throw new UnsupportedOperationException("Unsupported type of command: " + cmd.getClass().getSimpleName());
      }
    }
  }

  private void processPanPlayerCommand(BanPlayer cmd) {
    // TODO
  }

  private void processChangeMapCommand(ChangeMap cmd) {
    // TODO
  }

  private void processChangeNextMapCommand(ChangeNextMap cmd) {
    // TODO
  }

  private void processKickPlayerCommand(KickPlayer cmd) {
    // TODO
  }

  private void processRestartMapCommand(RestartMap cmd) {
    // TODO
  }

  private void processSystemCommand(SystemCommand cmd) {
    // TODO
  }

}
