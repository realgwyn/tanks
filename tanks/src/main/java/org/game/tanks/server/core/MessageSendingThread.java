package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Separate thread used to send NetworkMessages
 */
@Component
public class MessageSendingThread extends Thread {

  private final static Logger logger = Logger.getLogger(MessageSendingThread.class);
  private boolean running = true;

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  @Override
  public void run() {
    logger.debug("Running...");
    running = true;
    while (running) {
      sendOutgoingMessages();
      try {
        sleep(5);// Sleep a little bit
      } catch (InterruptedException e) {
      }
    }
  }

  public void finish() {
    logger.debug("Stopping...");
    running = false;
  }

  private void sendOutgoingMessages() {
    // XXX TODO: Investigate performance: GameEvents maybe should be sent instantly while being processed in main loop?
    while (!ctx.getOutgoingGameEvents().isEmpty()) {
      networkAdapter.sendToAllTCP(ctx.getOutgoingGameEvents().poll());
    }
    while (!ctx.getOutgoingCommands().isEmpty()) {
      networkAdapter.sendToAllTCP(ctx.getOutgoingCommands().poll());
    }
    while (!ctx.getOutgoingCommunicationMessages().isEmpty()) {
      sendCommunicationMessage(ctx.getOutgoingCommunicationMessages().poll());
    }
  }

  private void sendCommunicationMessage(CommunicationMessage msg) {
    if (msg.getPlayerIdTo() != 0) {
      PlayerServerModel player = ctx.getPlayerById(msg.getPlayerIdTo());
      if (player != null) {
        networkAdapter.sendTCP(player, msg);
      }
    } else {
      networkAdapter.sendToAllTCP(msg);
    }
  }

}
