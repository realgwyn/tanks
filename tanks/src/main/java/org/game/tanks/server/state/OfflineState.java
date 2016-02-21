package org.game.tanks.server.state;

import org.springframework.stereotype.Component;

@Component
public class OfflineState extends ServerState {

  @Override
  public void onStateBegin() {
    // Shut down all network listening threads
  }

  @Override
  public void update() {
    // not accepting network data
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onStateEnd() {
    // Start all network listening threads
  }

}
