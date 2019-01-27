package io.tanks.client.state;

import org.springframework.beans.factory.annotation.Autowired;

import io.tanks.client.core.ClientContext;
import io.tanks.common.network.state.ClientStateType;

public class LeavingMatchState extends ClientState {

  @Autowired
  ClientContext clientContext;
//  @Autowired
//  ServerController serverController;

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
      
//      serverController.stopServer();
      throw new UnsupportedOperationException("TODO: send admin command to stop server");
    }
  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }
}
