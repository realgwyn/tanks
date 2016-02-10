package org.game.tanks.network.model.message;

import java.util.Date;

import org.game.tanks.network.model.TCPMessage;

public class ServerMessage extends TCPMessage {

  private static final long serialVersionUID = 1391672655379297346L;
  private String playerIdTo;
  private Date time;
  private String text;

  public String getPlayerIdTo() {
    return playerIdTo;
  }

  public void setPlayerIdTo(String playerIdTo) {
    this.playerIdTo = playerIdTo;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
