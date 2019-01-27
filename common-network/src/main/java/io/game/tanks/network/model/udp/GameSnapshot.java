package io.game.tanks.network.model.udp;

import io.game.tanks.network.model.UDPMessage;

public class GameSnapshot extends UDPMessage {

  private static final long serialVersionUID = 8433956624835074364L;
  public long sequenceNumber;
  public boolean sequenceFlipFlag;
  public PlayerPosition[] positions;

  public GameSnapshot() {

  }

}
