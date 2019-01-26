package org.game.tanks.network.model.command;

import org.game.tanks.model.MapObject;
import org.game.tanks.network.model.TCPMessage;

public class MapInfoData extends TCPMessage {

  private static final long serialVersionUID = 9047479243297398880L;
  private String mapName;
  private int width;
  private int height;
  private MapObject[] objects;

  public MapInfoData() {

  }

  public String getMapName() {
    return mapName;
  }

  public MapInfoData setMapName(String mapName) {
    this.mapName = mapName;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public MapInfoData setWidth(int width) {
    this.width = width;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public MapInfoData setHeight(int height) {
    this.height = height;
    return this;
  }

  public MapObject[] getObjects() {
    return objects;
  }

  public MapInfoData setObjects(MapObject[] objects) {
    this.objects = objects;
    return this;
  }

  @Override
  public String toString() {
    return "MapInfoData [mapName=" + mapName + "]";
  }

}
