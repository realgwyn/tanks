package org.game.tanks.utils;

import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.model.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapService {

  @Autowired
  private ServerContext context;
  @Autowired
  private Config config;

  private List<String> mapNames;

  @PostConstruct
  public void init() {
    mapNames = config.getPropertyListString(Config.MAP_NAMES);
  }

  public void loadNextMap() {
    if (context.getCurrentMap() == null) {
      context.setCurrentMap(loadMap(mapNames.get(nextMapIndex())));
      context.setNextMap(loadMap(mapNames.get(nextMapIndex())));
    } else {
      context.setCurrentMap(context.getNextMap());
      context.setNextMap(loadMap(mapNames.get(nextMapIndex())));
    }
  }

  private int currentMapIndex = 0;

  private int nextMapIndex() {
    currentMapIndex++;
    if (currentMapIndex >= mapNames.size()) {
      currentMapIndex = 0;
    }
    return currentMapIndex;
  }

  private MapModel loadMap(String mapName) {
    MapModel map = new MapModel();
    // TODO parse map file
    return map;
  }

}
