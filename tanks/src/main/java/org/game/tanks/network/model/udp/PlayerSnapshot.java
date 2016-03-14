package org.game.tanks.network.model.udp;

import org.game.tanks.network.model.UDPMessage;

public class PlayerSnapshot extends UDPMessage {

  private static final long serialVersionUID = -1793664728806830611L;
  public long sequenceNumber;
  public boolean sequenceFlipFlag;
  public int playerId;
  public int x;
  public int y;
  public float bodyAngle;
  public float towerAngle;

  public PlayerSnapshot() {

  }

}
