package org.game.tanks.server.state;

public class IdleServerState extends ServerState {

  public IdleServerState() {
    super(ServerStateType.IDLE);
  }

  @Override
  public void update() {
    // do nothing
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
