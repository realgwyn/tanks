package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.core.Loop;
import org.game.tanks.state.State;
import org.springframework.stereotype.Component;

@Component
public class ServerEngine extends Loop {

  private final static Logger logger = Logger.getLogger(ServerEngine.class);
  private State currentState;

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

  @Override
  public void startup() {
    // TODO Auto-generated method stub

  }

  @Override
  public void shutdown() {
    logger.debug("Shutting down game threads...");
    System.exit(0);
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render() {
    // TODO Auto-generated method stub

  }

}
