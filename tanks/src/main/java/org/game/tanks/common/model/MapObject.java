package org.game.tanks.common.model;

import java.awt.Polygon;
import java.io.Serializable;

public class MapObject implements Serializable {

  private static final long serialVersionUID = 7681325751830093812L;
  private int posX;
  private int posY;
  private Polygon shape;
  private int texture;
  private boolean solid;// If not solid, it can be destroyed
  private int hp;

  public int getPosX() {
    return posX;
  }

  public MapObject setPosX(int posX) {
    this.posX = posX;
    return this;
  }

  public int getPosY() {
    return posY;
  }

  public MapObject setPosY(int posY) {
    this.posY = posY;
    return this;
  }

  public Polygon getShape() {
    return shape;
  }

  public MapObject setShape(Polygon shape) {
    this.shape = shape;
    return this;
  }

  public int getTexture() {
    return texture;
  }

  public MapObject setTexture(int texture) {
    this.texture = texture;
    return this;
  }

  public boolean isSolid() {
    return solid;
  }

  public MapObject setSolid(boolean solid) {
    this.solid = solid;
    return this;
  }

  public int getHp() {
    return hp;
  }

  public MapObject setHp(int hp) {
    this.hp = hp;
    return this;
  }

}
