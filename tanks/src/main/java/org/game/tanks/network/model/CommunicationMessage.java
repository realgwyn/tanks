package org.game.tanks.network.model;

import java.util.Date;

public class CommunicationMessage extends TCPMessage {

  private static final long serialVersionUID = 7368498129812636388L;

  private long playerIdTo;
  private Date time;
  private String text;

  public long getPlayerIdTo() {
    return playerIdTo;
  }

  public void setPlayerIdTo(long playerIdTo) {
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
