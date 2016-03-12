package org.game.tanks.server.core.state;

public abstract class ServerState {

  public enum ServerStateType {
    MATCH_INIT, WAITING_FOR_PLAYERS, ROUND_INIT, ROUND_START, ROUND, ROUND_END, MATCH_END, OFFLINE
  }

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
