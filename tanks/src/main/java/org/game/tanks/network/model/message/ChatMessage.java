package org.game.tanks.network.model.message;

import org.game.tanks.network.model.CommunicationMessage;

public class ChatMessage extends CommunicationMessage {

  private static final long serialVersionUID = 4963779786005611800L;
  private String playerIdFrom;

  public String getPlayerIdFrom() {
    return playerIdFrom;
  }

  public void setPlayerIdFrom(String playerIdFrom) {
    this.playerIdFrom = playerIdFrom;
  }

}
