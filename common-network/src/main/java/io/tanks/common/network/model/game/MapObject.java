package io.tanks.common.network.model.game;


import java.awt.*;
import java.io.Serializable;

public class MapObject implements Serializable {

  private static final long serialVersionUID = 7681325751830093812L;
  private int id;
  private Shape shape;
  private int texture;
  private boolean solid;// If not solid, it can be destroyed
  private int hp;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Shape getShape() {
    return shape;
  }

  public MapObject setShape(Shape shape) {
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
