package org.game.tanks.server.core.process;

import org.game.tanks.common.model.PlayerState;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.event.HitEvent;
import org.game.tanks.network.model.event.KillEvent;
import org.game.tanks.network.model.event.ShootEvent;
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

  @Override
  public void runProcess() {
    processGameEvents();
  }

  private void processGameEvents() {
    while (!context.getIncomingGameEvents().isEmpty()) {
      GameEvent event = context.getIncomingGameEvents().poll();
      if (event instanceof ShootEvent) {
        processShootEvent((ShootEvent) event);
      } else {
        throw new UnsupportedOperationException("Server cannot process GameEvent " + event.getClass());
      }
    }
  }

  private void processShootEvent(ShootEvent event) {
    context.getOutgoingGameEvents().add(event);
    for (PlayerServerModel player : context.getPlayers()) {
      if (player.getPlayerId() != event.getPlayerId() && player.getState() == PlayerState.ALIVE
          && player.getShape().contains(event.getPoint())) {
        context.getOutgoingGameEvents().add(new HitEvent()
            .setDamage(event.getDamage())
            .setPlayerId(event.getPlayerId()));

        player.setHealth(player.getHealth() - event.getDamage());

        if (player.getHealth() <= 0) {
          player.setState(PlayerState.DEAD);
          context.getOutgoingGameEvents().add(new KillEvent()
              .setPlayerId(player.getPlayerId()));
        }
        break;
      }
    }
  }

}
