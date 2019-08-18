package io.tanks.server.gameplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.server.core.ServerContext;
import io.tanks.server.core.process.ProcessSchedulerContext;
import io.tanks.server.model.PlayerServerModel;

@Component
public class Deathmatch extends GameType {

  @Autowired
  ServerContext serverContext;
  @Autowired
  ProcessSchedulerContext schedulerContext;

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

  @Override
  public void initializePlayersProperties(List<PlayerServerModel> players) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean playersAreReadyForNewMatch() {
    // TODO Auto-generated method stub
    return false;
  }

}
