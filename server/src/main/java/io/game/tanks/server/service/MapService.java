package io.game.tanks.server.service;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.model.command.MapInfoData;
import io.game.tanks.network.model.game.MapModel;
import io.game.tanks.network.model.game.MapObject;
import io.game.tanks.server.cfg.ServerConfig;

@Component
public class MapService {

  @Autowired
  private ServerConfig config;

  private List<String> mapNames;

  @PostConstruct
  public void init() {
    mapNames = config.getPropertyListString(ServerConfig.MAP_NAMES);
  }

  public MapModel loadMap(String mapName) {
    MapModel map = new MapModel();
    map.setMapName(mapName);
    map.setHeight(5000);
    map.setWidth(5000);
    map = generateMapObjects(map);
    // TODO parse map from file
    return map;
  }

  private int currentMapIndex = 0;

  public String getNextMapName() {
    String mapName = mapNames.get(currentMapIndex);
    currentMapIndex++;
    if (currentMapIndex >= mapNames.size()) {
      currentMapIndex = 0;
    }

    return mapName;
  }

  public static MapModel createMapModel(MapInfoData data) {
    return new MapModel()
        .setMapName(data.getMapName())
        .setWidth(data.getWidth())
        .setHeight(data.getHeight())
        .setObjects(new ArrayList<>(Arrays.asList(data.getObjects())));
  }

  public static MapInfoData createMapInfoData(MapModel model) {
    return new MapInfoData()
        .setMapName(model.getMapName())
        .setWidth(model.getWidth())
        .setHeight(model.getHeight())
        .setObjects(model.getObjects().toArray(new MapObject[0]));
  }

  private MapModel generateMapObjects(MapModel map) {

    MapObject borderTop = new MapObject();
    borderTop.setShape(new Rectangle(0, 0, 5000, 10));
    map.getObjects().add(borderTop);

    MapObject borderRight = new MapObject();
    borderRight.setShape(new Rectangle(4990, 0, 10, 5000));
    map.getObjects().add(borderRight);

    MapObject borderBottom = new MapObject();
    borderBottom.setShape(new Rectangle(0, 4990, 5000, 10));
    map.getObjects().add(borderBottom);

    MapObject borderLeft = new MapObject();
    borderLeft.setShape(new Rectangle(0, 0, 10, 5000));
    map.getObjects().add(borderLeft);

    return map;
  }

}
