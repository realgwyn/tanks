package org.game.tanks.server.state;

public class IdleState extends ServerState {

  public IdleState() {
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
