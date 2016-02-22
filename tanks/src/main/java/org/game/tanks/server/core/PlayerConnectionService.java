package org.game.tanks.server.core;

import java.util.Iterator;

import org.game.tanks.network.ServerNetworkAdapter;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerConnectionService {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  public void processPlayerConnections() {
    while (!ctx.getIncomingPlayers().isEmpty()) {
      PlayerServerModel newPlayer = ctx.getIncomingPlayers().poll();

      // Send initialization commands to player
      // Send map info
      // Send game progress info
      // Send players info
      // Send last 100 messages chat history
      // Send stats info

      // Broadcast to all about new incoming player

      ctx.getPlayers().add(newPlayer);
    }

    // TODO process leaving players
    Iterator<PlayerServerModel> it = ctx.getPlayers().iterator();
    while (it.hasNext()) {

    }
  }

}
