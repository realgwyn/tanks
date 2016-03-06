package org.game.tanks.server.service;

import org.game.tanks.client.state.ClientState.ClientStateType;
import org.game.tanks.network.model.command.SyncTime;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.state.ServerState.ServerStateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncStateService {

  @Autowired
  ServerContext ctx;

  public SyncTime createNewSyncTimeEvent() {
    return new SyncTime()
        .setMatchStartTime(ctx.getMatchStartTime())
        .setMatchEndTime(ctx.getMatchEndTime())
        .setRoundEndTime(ctx.getRoundEndTime());
  }

  public ClientStateType resolveClientState(ServerStateType serverState) {
    // TODO: implement this
    return null;
  }

}
