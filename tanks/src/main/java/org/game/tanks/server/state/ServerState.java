package org.game.tanks.server.state;

public abstract class ServerState {

  public enum ServerStateType {
    BEFORE_ROUND, ROUND, AFTER_ROUND, LOADING_MAP, OFFLINE, WAITING_FOR_PLAYERS, IDLE
  }

  private ServerStateType type;

  public ServerState(ServerStateType type) {
    this.type = type;
  }

  public ServerStateType getType() {
    return type;
  }

  public abstract void update();

  public void onStateBegin() {
  }

  public void onStateEnd() {
  }

}
