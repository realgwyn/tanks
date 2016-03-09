package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
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
  EventBus bus;
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
        // XXX Performance: this sleep might be too long
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
    while (!bus.getOutgoingGameEvents().isEmpty()) {
      networkAdapter.sendToAllTCP(bus.getOutgoingGameEvents().poll());
    }
    while (!bus.getOutgoingCommands().isEmpty()) {
      sendCommand(bus.getOutgoingCommands().poll());
    }
    while (!bus.getOutgoingCommunicationMessages().isEmpty()) {
      sendCommunicationMessage(bus.getOutgoingCommunicationMessages().poll());
    }
  }

  private void sendCommand(Command cmd) {
    if (cmd.getPlayerToId() != 0) {
      networkAdapter.sendTCP(cmd.getPlayerToId(), cmd);
    } else {
      networkAdapter.sendToAllTCP(cmd);
    }
  }

  private void sendCommunicationMessage(CommunicationMessage msg) {
    if (msg.getConnectionIdTo() != 0) {
      networkAdapter.sendTCP(msg.getConnectionIdTo(), msg);
    } else {
      networkAdapter.sendToAllTCP(msg);
    }
  }

}
