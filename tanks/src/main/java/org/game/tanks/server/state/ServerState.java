package org.game.tanks.server.state;

public abstract class ServerState {

  public abstract void update();

  public void onStateBegin() {
  }

  public void onStateEnd() {
  }

}
