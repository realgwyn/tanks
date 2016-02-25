package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class PlayerConnectionThread extends Thread {

  private final static Logger logger = Logger.getLogger(PlayerConnectionThread.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  List<Long> generatedIds;
  long idCount;

  private boolean running = true;

  private void initialize() {
    generatedIds = new ArrayList<>();
    idCount = 0;
  }

  @Override
  public void run() {
    initialize();
    logger.debug("Running...");
    running = true;
    while (running) {
      processPlayerConnections();
      try {
        sleep(250);// Sleep a little bit
      } catch (InterruptedException e) {
      }
    }
  }

  public void finish() {
    logger.debug("Stopping...");
    running = false;
  }

  public void processPlayerConnections() {
    processIncomingPlayers();
    processLeavingPlayers();
  }

  private void processLeavingPlayers() {
    while (networkAdapter.hasClosedConnections()) {
      Connection con = networkAdapter.pollClosedConnection();
      PlayerServerModel player = getPlayerByConnectionId(con.getID());
      if (player != null) {
        // TODO finalize connection with player - Send some packets related with disconnecting
        ctx.getLeavingPlayerIds().add(player.getPlayerId());
      }
    }
  }

  private void processIncomingPlayers() {
    while (networkAdapter.hasNewConnections()) {

      // TODO: check server new connections
      Connection con = networkAdapter.pollNewConnection();
      PlayerServerModel player = createPlayerServerModel();

      // TODO handshake with new player
      // Send initialization commands to player
      // Send map info
      // Send game progress info
      // Send players info
      // Send last 100 messages chat history
      // Send stats info

      // Broadcast to all about new incoming player

      ctx.getIncomingPlayers().add(player);
    }
  }

  private PlayerServerModel createPlayerServerModel() {
    PlayerServerModel p = new PlayerServerModel(generateNextId());
    // TODO: set fields
    return p;
  }

  private PlayerServerModel getPlayerByConnectionId(int connectionId) {
    for (PlayerServerModel player : ctx.getPlayers()) {
      if (player.getConnection().getID() == connectionId) {
        return player;
      }
    }
    return null;
  }

  private long generateNextId() {
    while (generatedIds.contains(idCount)) {
      idCount++;
    }
    return idCount;
  }

}
