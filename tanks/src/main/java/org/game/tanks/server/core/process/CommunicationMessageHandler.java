package org.game.tanks.server.core.process;

import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerNetworkAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processes Player & Server Messages
 */
@Component
public class CommunicationMessageHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  @Override
  public void runProcess() {
    processCommunitactionMessages();
  }

  private void processCommunitactionMessages() {
    while (!ctx.getIncomingCommunicationMessages().isEmpty()) {
      CommunicationMessage msg = ctx.getIncomingCommunicationMessages().poll();
      if (msg instanceof ChatMessage) {
        processChatMessage((ChatMessage) msg);
      } else {
        throw new UnsupportedOperationException("Undefined type of message: " + msg.getClass().getSimpleName());
      }
    }
  }

  private void processChatMessage(ChatMessage msg) {
    ctx.getOutgoingCommunicationMessages().add(msg);
  }

}
