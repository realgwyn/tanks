package org.game.tanks.server.core;

import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerConnectionService {

  @Autowired
  ServerContext serverContext;

  public void processIncomingPlayers() {
    while (!serverContext.getIncomingPlayers().isEmpty()) {
      PlayerServerModel newPlayer = serverContext.getIncomingPlayers().poll();

      // Send initialization commands to player
      // Send map info
      // Send game progress info
      // Send players info
      // Send last 100 messages chat history
      // Send stats info

      // Broadcast to all about new incoming player

      serverContext.getPlayers().add(newPlayer);
    }

  }

}
