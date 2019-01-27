package io.tanks.common.network.model.command;

import io.tanks.common.network.model.message.ChatMessage;
import io.tanks.common.network.model.TCPMessage;

public class ChatHistory extends TCPMessage {

  private static final long serialVersionUID = -5739410349768273663L;
  private ChatMessage[] chatHistory;

  public ChatHistory() {

  }

  public ChatMessage[] getChatHistory() {
    return chatHistory;
  }

  public ChatHistory setChatHistory(ChatMessage[] chatHistory) {
    this.chatHistory = chatHistory;
    return this;
  }

}
