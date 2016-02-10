package org.game.tanks.network.model.message;

import java.util.Date;

import org.game.tanks.network.model.TCPMessage;

public class ChatMessage extends TCPMessage{
  
  private static final long serialVersionUID = 4963779786005611800L;
  private String playerIdFrom;
  private String playerIdTo;
  private Date time;
  private String text;
  
  public String getPlayerIdFrom() {
    return playerIdFrom;
  }
  public void setPlayerIdFrom(String playerIdFrom) {
    this.playerIdFrom = playerIdFrom;
  }
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
