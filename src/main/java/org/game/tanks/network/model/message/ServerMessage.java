package org.game.tanks.network.model.message;

import java.util.Date;

import org.game.tanks.network.model.CommunicationMessage;

public class ServerMessage extends CommunicationMessage {

  private static final long serialVersionUID = 1391672655379297346L;

  public enum ServerMessageType {
    OK, FORBIDDEN, PLAYER_MESSAGE, SYSTEM_MESSAGE
  }

  private ServerMessageType type;

  public ServerMessage() {
    time = new Date();
  }

  public ServerMessageType getType() {
    return type;
  }

  public ServerMessage setType(ServerMessageType type) {
    this.type = type;
    return this;
  }

}
