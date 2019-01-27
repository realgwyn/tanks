package io.tanks.server.core.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.server.core.ServerEventBus;
import io.tanks.server.model.PlayerServerModel;

@Component
public class PlayerConnectionHandler extends ScheduledProcess {

  @Autowired
  ServerEventBus bus;
  @Autowired
  SchedulerContext schedulerCtx;

  @Override
  public void runProcess() {
    while (!bus.getIncomingPlayers().isEmpty()) {
      PlayerServerModel newPlayer = bus.getIncomingPlayers().poll();
      schedulerCtx.addPlayer(newPlayer);
    }
    while (!bus.getLeavingPlayerIds().isEmpty()) {
      long playerId = bus.getLeavingPlayerIds().poll();
      schedulerCtx.removePlayer(playerId);
    }
  }

}
