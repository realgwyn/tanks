package io.tanks.server.core.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.model.CommunicationMessage;
import io.tanks.common.network.model.message.ChatMessage;
import io.tanks.server.cfg.ServerConfig;
import io.tanks.server.core.ServerContext;
import io.tanks.server.core.ServerEventBus;

/**
 * Processes Player Chat Messages
 */
@Component
public class CommunicationMessageHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerEventBus bus;
  @Autowired
  ServerConfig config;

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
