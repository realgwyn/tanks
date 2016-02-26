package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.game.tanks.network.model.Handshake;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.utils.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class PlayerConnectionThread implements Runnable {

  private final static Logger logger = Logger.getLogger(PlayerConnectionThread.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  private List<Long> generatedIds;
  private long idCount;
  private boolean running;

  public synchronized void start() {
    new Thread(this).start();
  }

  private void initialize() {
    generatedIds = new ArrayList<>();
    idCount = 0;
    running = true;
  }

  @Override
  public void run() {
    initialize();
    logger.debug("Running...");
    running = true;
    while (running) {
      processPlayerConnections();
      try {
        Thread.sleep(250);// Sleep a little bit
      } catch (InterruptedException e) {
      }
    }
  }

  public void finish() {
    logger.debug("Stopping...");
    running = false;
  }

  public void processPlayerConnections() {
    processNewConnections();
    processIncomingHandshakes();
    processLeavingPlayers();
  }

  private void processIncomingHandshakes() {
    while (!ctx.getIncomingHandshakes().isEmpty()) {

    }
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

  private void processNewConnections() {
    while (networkAdapter.hasNewConnections()) {

      // TODO: check server new connections
      Connection connection = networkAdapter.pollNewConnection();
      long newId = generateNextId();
      PlayerServerModel player = new PlayerServerModel(newId, connection);
      Handshake handshake = new Handshake()
          .setConnId(connection.getID())
          .setPlayerId(newId)
          .setPlayerName("");

      networkAdapter.sendTCP(connection, handshake);
      networkAdapter.sendTCP(connection, MapService.createMapInfoData(ctx.getCurrentMap()));

      // TODO handshake with new player
      // Send initialization commands to player
      // Send map info
      // Send game progress info
      // Send players info
      // Send last 100 messages chat history
      // Send stats info

      // Broadcast to all about new incoming player

      ctx.getPendingPlayers().add(player);
    }
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
