package io.tanks.common.network.model.message;

import io.tanks.common.network.model.CommunicationMessage;

public class ChatMessage extends CommunicationMessage {

  private static final long serialVersionUID = 4963779786005611800L;
  private String playerIdFrom;

  public ChatMessage() {

  }

  public String getPlayerIdFrom() {
    return playerIdFrom;
  }

  public ChatMessage setPlayerIdFrom(String playerIdFrom) {
    this.playerIdFrom = playerIdFrom;
    return this;
  }

  @Override
  public String toString() {
    return "ChatMessage [playerIdFrom=" + playerIdFrom + ", time=" + time + ", text=" + text + "]";
  }

}
