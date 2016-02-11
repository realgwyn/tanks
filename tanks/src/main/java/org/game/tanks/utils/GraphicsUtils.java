package org.game.tanks.utils;

import java.awt.Polygon;

public class GraphicsUtils {

  public static Polygon scale(Polygon polygon, float ratio) {
    return scale(polygon, ratio, ratio);
  }

  public static Polygon scale(Polygon polygon, float widthRatio, float heightRatio) {
    Polygon scaledPolygon = new Polygon();
    scaledPolygon.xpoints = new int[polygon.npoints];
    scaledPolygon.ypoints = new int[polygon.npoints];
    for (int i = 0; i < scaledPolygon.xpoints.length; i++) {
      scaledPolygon.xpoints[i] = (int) (polygon.xpoints[i] * widthRatio);
    }
    for (int i = 0; i < scaledPolygon.ypoints.length; i++) {
      scaledPolygon.ypoints[i] = (int) (polygon.ypoints[i] * heightRatio);
    }
    return scaledPolygon;
  }

}
