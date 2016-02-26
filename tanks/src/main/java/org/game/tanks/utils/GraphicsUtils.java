package org.game.tanks.utils;

import java.awt.Polygon;

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

  public static void main(String[] args) {
    Polygon p = new Polygon();
    p.addPoint(2, 2);
    p.addPoint(4, 2);
    p.addPoint(4, 4);
    p.addPoint(2, 4);

  }

}
