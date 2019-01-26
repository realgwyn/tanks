package org.game.tanks.client.state;

import org.game.tanks.client.core.ClientContext;
import org.game.tanks.server.core.ServerController;
import org.springframework.beans.factory.annotation.Autowired;

public class LeavingMatchState extends ClientState {

  @Autowired
  ClientContext clientContext;
  @Autowired
  ServerController serverController;

  public LeavingMatchState(ClientStateType type) {
    super(ClientStateType.LEAVING_MATCH);
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public void draw() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onStateBegin() {
    if (clientContext.isHostingGame()) {
      clientContext.setHostingGame(false);
      serverController.stopServer();
    }
  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }
}
