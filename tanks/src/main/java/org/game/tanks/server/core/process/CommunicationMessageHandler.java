package org.game.tanks.server.core.process;

import java.util.Queue;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerNetworkAdapter;
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
  ServerNetworkAdapter networkAdapter;
  @Autowired
  Config config;

  private int maxChatHistorySize = 1000;

  @PostConstruct
  public void init() {
    maxChatHistorySize = config.getPropertyInt(Config.SERVER_MAX_CHAT_HISTORY_SIZE);
  }

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
    Queue<ChatMessage> chatHistory = ctx.getChatHistory();
    chatHistory.add(msg);
    if (chatHistory.size() > maxChatHistorySize) {
      chatHistory.poll();
    }
    ctx.getOutgoingCommunicationMessages().add(msg);
  }

}
