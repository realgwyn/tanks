package io.tanks.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.model.command.ChangeState;
import io.tanks.common.network.model.command.SyncTime;
import io.tanks.common.network.state.ClientStateType;
import io.tanks.common.network.state.ServerStateType;
import io.tanks.server.core.ServerContext;
import io.tanks.server.core.ServerEventBus;

@Component
public class SyncStateService {

  @Autowired
  ServerContext serverCtx;
  @Autowired
  ServerEventBus bus;

  public SyncTime createNewSyncTimeEvent() {
    return new SyncTime()
        .setMatchStartTime(serverCtx.getMatchStartTime())
        .setMatchEndTime(serverCtx.getMatchEndTime())
        .setRoundEndTime(serverCtx.getRoundEndTime());
  }

  public static ClientStateType resolveClientState(ServerStateType serverState) {
    switch (serverState) {
    case MATCH_INIT:
      return ClientStateType.MATCH_INIT;
    case WAITING_FOR_PLAYERS:
      return ClientStateType.WAITING_FOR_PLAYERS;
    case ROUND_START:
      return ClientStateType.ROUND_START;
    case ROUND:
      return ClientStateType.ROUND;
    case ROUND_END:
      return ClientStateType.ROUND_END;
    case MATCH_END:
      return ClientStateType.MATCH_END;
    default:
      return ClientStateType.FIND_GAME_ONLINE_MENU;
    }
  }

  public void syncClients(ServerStateType nextStateType, long timeUntilNextState) {
    long nextStateStartTime = System.currentTimeMillis() + timeUntilNextState;
    ChangeState changeStateRequest = new ChangeState()
        .setType(SyncStateService.resolveClientState(nextStateType))
        .setChangeStateTime(nextStateStartTime);
    bus.getOutgoingCommands().add(changeStateRequest);
  }
}
