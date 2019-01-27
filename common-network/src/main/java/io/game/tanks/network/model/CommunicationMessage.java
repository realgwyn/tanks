package io.game.tanks.network.model;

import java.util.Date;

public class CommunicationMessage extends TCPMessage {

  private static final long serialVersionUID = 7368498129812636388L;

  protected Date time;
  protected String text;

  public Date getTime() {
    return time;
  }

  public CommunicationMessage setTime(Date time) {
    this.time = time;
    return this;
  }

  public String getText() {
    return text;
  }

  public CommunicationMessage setText(String text) {
    this.text = text;
    return this;
  }

}
