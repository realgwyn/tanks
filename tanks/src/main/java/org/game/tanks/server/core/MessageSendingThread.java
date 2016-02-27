package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Separate thread used to send NetworkMessages
 */
@Component
public class MessageSendingThread implements Runnable {

  private final static Logger logger = Logger.getLogger(MessageSendingThread.class);
  private boolean running = true;

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    logger.debug("Running...");
    running = true;
    while (running) {
      sendOutgoingMessages();
      try {
        Thread.sleep(5);// Sleep a little bit
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
      sendCommand(ctx.getOutgoingCommands().poll());
    }
    while (!ctx.getOutgoingCommunicationMessages().isEmpty()) {
      sendCommunicationMessage(ctx.getOutgoingCommunicationMessages().poll());
    }
  }

  private void sendCommand(Command cmd) {
    if (cmd.getPlayerToId() != 0) {
      PlayerServerModel player = ctx.getPlayerById(cmd.getPlayerToId());
      if (player != null) {
        networkAdapter.sendTCP(player, cmd);
      }
    } else {
      networkAdapter.sendToAllTCP(cmd);
    }

  }

  private void sendCommunicationMessage(CommunicationMessage msg) {
    if (msg.getConnectionIdTo() != 0) {
      networkAdapter.sendTCP(msg.getConnectionIdTo(), msg);
    } else if (msg.getPlayerIdTo() != 0) {
      PlayerServerModel player = ctx.getPlayerById(msg.getPlayerIdTo());
      if (player != null) {
        networkAdapter.sendTCP(player, msg);
      }
    } else {
      networkAdapter.sendToAllTCP(msg);
    }
  }

}
