package io.tanks.client.state.menu;

import org.springframework.beans.factory.annotation.Autowired;

import io.tanks.client.core.GameEngine;
import io.tanks.client.state.ClientState;
import io.tanks.common.network.state.ClientStateType;

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
