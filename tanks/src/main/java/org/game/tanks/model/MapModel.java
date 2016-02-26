package org.game.tanks.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapModel implements Serializable {

  private static final long serialVersionUID = -6692869560887603657L;
  private String mapName;
  private int width;
  private int height;
  private MapObject[][] objectsPositions;
  private List<MapObject> objects;

  public MapModel() {
    objectsPositions = new MapObject[1][1];
    objects = new ArrayList<>();
    width = 1000;
    height = 1000;
  }

  public String getMapName() {
    return mapName;
  }

  public MapModel setMapName(String mapName) {
    this.mapName = mapName;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public MapModel setWidth(int width) {
    this.width = width;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public MapModel setHeight(int height) {
    this.height = height;
    return this;
  }

  public MapModel setObjects(List<MapObject> objects) {
    this.objects = objects;
    objectsPositions = new MapObject[width][height];
    for (MapObject object : objects) {
      objectsPositions[object.getPosX()][object.getPosY()] = object;
    }
    return this;
  }

  public List<MapObject> getObjects() {
    return objects;
  }

  public MapObject[][] getObjectsPositions() {
    return objectsPositions;
  }

}
