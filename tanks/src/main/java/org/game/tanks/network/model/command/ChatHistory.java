package org.game.tanks.network.model.command;

import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.message.ChatMessage;

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
