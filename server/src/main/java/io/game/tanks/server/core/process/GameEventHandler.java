package io.game.tanks.server.core.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.model.GameEvent;
import io.game.tanks.network.model.event.HitEvent;
import io.game.tanks.network.model.event.KillEvent;
import io.game.tanks.network.model.event.ShootEvent;
import io.game.tanks.network.state.PlayerState;
import io.game.tanks.server.core.ServerEventBus;
import io.game.tanks.server.model.PlayerServerModel;

/**
 * Processes GameEvents
 */
@Component
public class GameEventHandler extends ScheduledProcess {

  @Autowired
  private ServerEventBus bus;
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
      if (player.getConnectionId() != event.getPlayerId() && player.getState() == PlayerState.ALIVE
          && player.getModel().getShape().contains(event.getPoint())) {
        bus.getOutgoingGameEvents().add(new HitEvent()
            .setDamage(event.getDamage())
            .setPlayerId(event.getPlayerId()));

        player.setHealth(player.getHealth() - event.getDamage());

        if (player.getHealth() <= 0) {
          player.setState(PlayerState.DEAD);
          bus.getOutgoingGameEvents().add(new KillEvent()
              .setPlayerId(player.getConnectionId()));
        }
        break;
      }
    }
  }

}
