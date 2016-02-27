package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.network.model.Handshake;
import org.game.tanks.server.service.PlayerConnectionService;
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
  @Autowired
  PlayerConnectionService playerConnectionService;

  private boolean running;

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public void run() {
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

  private void processNewConnections() {
    while (networkAdapter.hasNewConnections()) {
      Connection connection = networkAdapter.pollNewConnection();
      playerConnectionService.initializeNewPlayerConnection(connection);
    }
  }

  private void processIncomingHandshakes() {
    while (!ctx.getIncomingHandshakes().isEmpty()) {
      Handshake handshake = ctx.getIncomingHandshakes().poll();
      playerConnectionService.addNewPlayerToTheGame(handshake);
    }
  }

  private void processLeavingPlayers() {
    while (networkAdapter.hasClosedConnections()) {
      Connection connection = networkAdapter.pollClosedConnection();
      playerConnectionService.removePlayerConnection(connection);
    }
  }

}
