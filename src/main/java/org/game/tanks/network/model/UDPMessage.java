package org.game.tanks.network.model;

import java.io.Serializable;

public class UDPMessage implements Serializable {

  private static final long serialVersionUID = -8803161634297879113L;
  private boolean newRoundFlipFlag;

  public boolean getNewRoundFlipFlag() {
    return newRoundFlipFlag;
  }

  public void setNewRoundFlipFlag(boolean newRoundFlipFlag) {
    this.newRoundFlipFlag = newRoundFlipFlag;
  }

}
