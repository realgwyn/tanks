package io.tanks.server.core.state;

import io.tanks.common.network.state.ServerStateType;

public abstract class ServerState {

  private ServerStateType type;
  private long startTime;

  public ServerState(ServerStateType type) {
    this.type = type;
  }

  public ServerStateType getType() {
    return type;
  }

  public abstract void update();

  public void stateBegin() {
    startTime = System.currentTimeMillis();
    onStateBegin();
  }

  public abstract void onStateBegin();

  public void stateEnd() {

    onStateEnd();
  }

  public abstract void onStateEnd();

  /**
   * @returns true - if time passed since state started is greater than timeDelta (milliseconds)
   */
  public boolean timePassed(long timeDelta) {
    long currentTime = System.currentTimeMillis();
    return currentTime - startTime > timeDelta;
  }

}
