package org.game.tanks.server.core.process;

import org.apache.log4j.Logger;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.command.ChangeName;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.Ping;
import org.game.tanks.network.model.command.Pong;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerNetworkAdapter;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processes GameCommands
 */
@Component
public class GameCommandHandler extends ScheduledProcess {

  private final static Logger logger = Logger.getLogger(GameCommandHandler.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  @Override
  public void runProcess() {
    while(!ctx.getIncomingCommands().isEmpty()){
      Command cmd = ctx.getIncomingCommands().poll();
      if(cmd instanceof ChangeName){
        processChangeNameCommand((ChangeName) cmd);
      } else if (cmd instanceof Disconnect) {
        processDisconnectCommand((Disconnect) cmd);
      } else if (cmd instanceof Ping) {
        processPingCommand((Ping) cmd);
      } else if (cmd instanceof Pong) {
        processPongCommand((Pong) cmd);
      }else{
        throw new UnsupportedOperationException("Message not supported " + cmd.getClass().getSimpleName());
      }
    }
  }

  private void processChangeNameCommand(ChangeName cmd) {
    if (!cmd.getNewPlayerName().isEmpty()) {
      PlayerServerModel player = ctx.getPlayerById(cmd.getPlayerId());
      logger.debug(player + " changed name to: " + cmd.getNewPlayerName());
      player.setPlayerName(cmd.getNewPlayerName());
      ctx.getOutgoingCommands().add(cmd);
    }
  }

  private void processDisconnectCommand(Disconnect cmd) {
    //TODO
  }

  private void processPingCommand(Ping cmd) {
    //TODO
  }

  private void processPongCommand(Pong cmd) {
    //TODO
  }

}
