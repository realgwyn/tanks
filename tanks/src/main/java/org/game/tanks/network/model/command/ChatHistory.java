package org.game.tanks.network.model.command;

import java.util.List;

import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.message.ChatMessage;

public class ChatHistory extends TCPMessage {

  private static final long serialVersionUID = -5739410349768273663L;
  private List<ChatMessage> chatHistory;

  public List<ChatMessage> getChatHistory() {
    return chatHistory;
  }

  public ChatHistory setChatHistory(List<ChatMessage> chatHistory) {
    this.chatHistory = chatHistory;
    return this;
  }

}
