package org.game.tanks.utils;


import java.awt.*;

public class GraphicsUtils {

  public static Polygon scale(Polygon polygon, float ratio) {
    return scale(polygon, ratio, ratio);
  }

  public static Polygon scale(Polygon polygon, float widthRatio, float heightRatio) {
    // TODO scale
    return polygon;
  }

  public static Polygon updatePlayerShape(Polygon shape, int posX, int posY, float bodyAngle, float towerAngle) {
    // TODO: rotate stuff
    // return path.createTransformedShape(transform).getBounds();
    return shape;
  }

}
