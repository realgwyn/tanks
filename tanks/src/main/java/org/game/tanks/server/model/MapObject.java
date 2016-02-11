package org.game.tanks.server.model;

import java.awt.Polygon;

public class MapObject {

  private int posX;
  private int posY;
  private Polygon shape;

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public Polygon getShape() {
    return shape;
  }

  public void setShape(Polygon shape) {
    this.shape = shape;
  }

}
