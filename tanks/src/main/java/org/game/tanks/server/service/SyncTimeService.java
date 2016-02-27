package org.game.tanks.server.service;

import org.game.tanks.network.model.command.SyncTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncTimeService {

  @Autowired
  Config cfg;
  @Autowired
  Context ctx;

  public SyncTime createNewSyncTimeEvent() {

  }

}
