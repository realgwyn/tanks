package org.game.tanks.utils;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

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

  public static Polygon updatePlayerShape(Polygon shape, int posX, int posY, float bodyAngle, float towerAngle) {
    Rectangle bounds = shape.getBounds();
    AffineTransform transform = new AffineTransform();
    transform.rotate(Math.toRadians(bodyAngle), bounds.width / 2, bounds.height / 2);
    Path2D path = new GeneralPath(shape);
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
