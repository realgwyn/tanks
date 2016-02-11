package org.game.tanks.server.core;

import org.game.tanks.server.model.MapModel;
import org.springframework.stereotype.Component;

@Component
public class ServerContext {

  private MapModel currentMap;
  private MapModel nextMap;

}
