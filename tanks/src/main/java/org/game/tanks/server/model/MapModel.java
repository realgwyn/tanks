package org.game.tanks.server.model;

import java.util.ArrayList;
import java.util.List;

public class MapModel {

  private int width;
  private int height;
  private MapObject[][] objectsPositions;
  private List<MapObject> objects;

  public MapModel() {
    objectsPositions = new MapObject[1][1];
    objects = new ArrayList<>();
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setObjects(List<MapObject> objects) {
    this.objects = objects;
    objectsPositions = new MapObject[width][height];
    for (MapObject object : objects) {
      objectsPositions[object.getPosX()][object.getPosY()] = object;
    }
  }

  public List<MapObject> getObjects() {
    return objects;
  }

  public MapObject[][] getObjectsPositions() {
    return objectsPositions;
  }

}
