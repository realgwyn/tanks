package org.game.tanks.client.state.menu;

import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.state.ClientState;
import org.springframework.beans.factory.annotation.Autowired;

public class ExitGameState extends ClientState {

  @Autowired
  GameEngine engine;

  public ExitGameState(ClientStateType type) {
    super(ClientStateType.EXIT_GAME);
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
    // TODO Auto-generated method stub

  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }

}
