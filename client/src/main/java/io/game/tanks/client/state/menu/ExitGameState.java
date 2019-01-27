package io.game.tanks.client.state.menu;

import org.springframework.beans.factory.annotation.Autowired;

import io.game.tanks.client.core.GameEngine;
import io.game.tanks.client.state.ClientState;
import io.game.tanks.network.state.ClientStateType;

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
