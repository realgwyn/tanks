package org.game.tanks.server.core.process;

import org.game.tanks.cfg.Config;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.server.core.EventBus;
import org.game.tanks.server.core.ServerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processes Player Chat Messages
 */
@Component
public class CommunicationMessageHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;
  @Autowired
  EventBus bus;
  @Autowired
  Config config;

  @Override
  public void runProcess() {
    processCommunitactionMessages();
  }

  private void processCommunitactionMessages() {
    while (!bus.getIncomingCommunicationMessages().isEmpty()) {
      CommunicationMessage msg = bus.getIncomingCommunicationMessages().poll();
      if (msg instanceof ChatMessage) {
        processChatMessage((ChatMessage) msg);
      } else {
        throw new UnsupportedOperationException("Undefined type of message: " + msg.getClass().getSimpleName());
      }
    }
  }

  private void processChatMessage(ChatMessage msg) {
    // TODO: display chat message in Server GUI if standalone
    bus.getOutgoingCommunicationMessages().add(msg);
  }

}
