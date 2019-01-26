package org.game.tanks.network.model;

import java.io.Serializable;

public class TCPMessage implements Serializable {

  private static final long serialVersionUID = 5745415005328673143L;
  private int connectionIdTo;
  private int connectionIdFrom;
  private boolean newRoundFlipFlag;

  public int getConnectionIdTo() {
    return connectionIdTo;
  }

  public void setConnectionIdTo(int connectionIdTo) {
    this.connectionIdTo = connectionIdTo;
  }

  public int getConnectionIdFrom() {
    return connectionIdFrom;
  }

  public void setConnectionIdFrom(int connectionIdFrom) {
    this.connectionIdFrom = connectionIdFrom;
  }

  public boolean getNewRoundFlipFlag() {
    return newRoundFlipFlag;
  }

  public void setNewRoundFlipFlag(boolean newRoundFlipFlag) {
    this.newRoundFlipFlag = newRoundFlipFlag;
  }

}
