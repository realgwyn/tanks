package org.game.tanks.network.model.udp;

import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.server.state.ServerState.ServerStateType;

public class GameSnapshot extends UDPMessage {

  private static final long serialVersionUID = 8433956624835074364L;
  public ServerStateType state;
  public long sequenceNumber;
  public boolean sequenceFlipFlag;
  public PlayerPosition[] positions;

}
