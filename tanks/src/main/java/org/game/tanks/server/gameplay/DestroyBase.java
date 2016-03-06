package org.game.tanks.server.gameplay;

import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.process.SchedulerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DestroyBase extends GameType {

  @Autowired
  ServerContext serverContext;
  @Autowired
  SchedulerContext schedulerContext;

  @Override
  public void reinitialize() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean roundObjectivesCompleted() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void scoreRoundTimeout() {
    // TODO Auto-generated method stub

  }

  @Override
  public void scoreRound() {
    // TODO Auto-generated method stub

  }

}
