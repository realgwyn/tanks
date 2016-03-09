package org.game.tanks.server.core.process;

import org.game.tanks.model.PlayerState;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.event.HitEvent;
import org.game.tanks.network.model.event.KillEvent;
import org.game.tanks.network.model.event.ShootEvent;
import org.game.tanks.server.core.EventBus;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processes GameEvents
 */
@Component
public class GameEventHandler extends ScheduledProcess {

  @Autowired
  private ServerContext context;
  @Autowired
  private EventBus bus;
  @Autowired
  private SchedulerContext schedulerCtx;

  @Override
  public void runProcess() {
    processGameEvents();
  }

  private void processGameEvents() {
    while (!bus.getIncomingGameEvents().isEmpty()) {
      GameEvent event = bus.getIncomingGameEvents().poll();
      if (event instanceof ShootEvent) {
        processShootEvent((ShootEvent) event);
      } else {
        throw new UnsupportedOperationException("Server cannot process GameEvent " + event.getClass());
      }
    }
  }

  private void processShootEvent(ShootEvent event) {
    bus.getOutgoingGameEvents().add(event);
    for (PlayerServerModel player : schedulerCtx.getPlayers()) {
      if (player.getConnectionId() != event.getPlayerId() && player.getModel().getState() == PlayerState.ALIVE
          && player.getModel().getShape().contains(event.getPoint())) {
        bus.getOutgoingGameEvents().add(new HitEvent()
            .setDamage(event.getDamage())
            .setPlayerId(event.getPlayerId()));

        player.getModel().setHealth(player.getModel().getHealth() - event.getDamage());

        if (player.getModel().getHealth() <= 0) {
          player.getModel().setState(PlayerState.DEAD);
          bus.getOutgoingGameEvents().add(new KillEvent()
              .setPlayerId(player.getConnectionId()));
        }
        break;
      }
    }
  }

}
