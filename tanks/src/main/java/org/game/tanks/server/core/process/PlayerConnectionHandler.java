package org.game.tanks.server.core.process;

import org.game.tanks.network.model.command.Connect;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerConnectionHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;

  @Override
  public void runProcess() {
    while (!ctx.getIncomingPlayers().isEmpty()) {
      PlayerServerModel newPlayer = ctx.getIncomingPlayers().poll();
      ctx.addPlayer(newPlayer);
      Connect connect = new Connect()
          .setPlayerId(newPlayer.getPlayerId())
          .setPlayerName(newPlayer.getPlayerName());
      ctx.getOutgoingCommands().add(connect);
    }
    while (!ctx.getLeavingPlayerIds().isEmpty()) {
      long playerId = ctx.getLeavingPlayerIds().poll();
      ctx.removePlayer(playerId);
    }
  }

}
