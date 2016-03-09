package org.game.tanks.server.core.state;

import org.game.tanks.server.core.ServerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundInitServerState extends ServerState {

  public RoundInitServerState() {
    super(ServerStateType.ROUND_INIT);
  }

  @Autowired
  ServerContext serverContext;

  @Override
  public void onStateBegin() {
    serverContext.setNewRoundFlipFlag(!serverContext.getNewRoundFlipFlag());
    serverContext.reinitialize();
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }

}
