package org.game.tanks.network.model.udp;

import org.game.tanks.network.model.UDPMessage;

public class GameSnapshot extends UDPMessage {

  private static final long serialVersionUID = 8433956624835074364L;
  public long id;
  public PlayerPosition[] positions;

}
