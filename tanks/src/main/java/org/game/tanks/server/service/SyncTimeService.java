package org.game.tanks.server.service;

import org.game.tanks.cfg.Config;
import org.game.tanks.network.model.command.SyncTime;
import org.game.tanks.server.core.ServerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncTimeService {

  @Autowired
  Config cfg;
  @Autowired
  ServerContext ctx;

  // TODO:
  public SyncTime createNewSyncTimeEvent() {
    // ctx.getMatchStartTime()
    // ctx.getMatchEndTime()
    // ctx.getRoundEndTime()
    // SyncTime syncTime = new SyncTime()
    // .setMatchStartTime(matchStartTime)
    // .setMatchEndTime(matchEndTime)
    // .setRoundEndTime();
    throw new UnsupportedOperationException("Not implemented yet");
  }

}
