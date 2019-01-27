package io.game.tanks.demo.graphics.shadows;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * @author Matt DesLauriers, mattdesl
 */
public class ShadowTest {

  public static void main(String[] args) {
    new ShadowTest().run();
  }

  /** The gradient radius for our shadow. */
  protected final static float GRADIENT_SIZE = 200f;

  /**
   * The fractions for our shadow gradient, going from 0.0 (black) to 1.0
   * (transparent).
   */
  protected final static float[] GRADIENT_FRACTIONS = new float[] { 0f, 1f };

  /**
   * The colors for our shadow, going from opaque black to transparent black.
   */
  protected final static Color[] GRADIENT_COLORS = new Color[] { Color.black, new Color(0f, 0f, 0f, 0f) };

  /** A Polygon object which we will re-use for each shadow geometry. */
  protected final static Polygon POLYGON = new Polygon();

  /** The size of the game canvas, initially 640x480. */
  private static int width = 640, height = 480;

  /** Returns the width of the game canvas. */
  public static int getWidth() {
    return Math.max(0, width);
  }

  /** Returns the height of the game canvas. */
  public static int getHeight() {
    return Math.max(0, height);
  }

  /** The buffer strategy used for smooth active rendering. */
  protected BufferStrategy strategy;

  /** True if the game loop is running. */
  protected boolean running;

  /** The current frames per second, used for debugging performance. */
  protected int fps = 60;

  /** Timer used for adding entities every N seconds. */
  private float tickTimer;

  /** A list of entities to render. */
  protected List<Shape> entities = new ArrayList<Shape>();

  /** The mouse position */
  protected int mouseX, mouseY;

  /////// GUI VARIABLES ///////

  /** The frame for our GUI. */
  protected JFrame frame = new JFrame("Shooter Game");

  /**
   * Constructs a new Game object; run() should be called to start the
   * rendering.
   */
  public ShadowTest() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //set up our Canvas which will be used for active rendering (game loop)
    final Canvas canvas = new Canvas();
    canvas.setIgnoreRepaint(true);
    canvas.setPreferredSize(new Dimension(width, height));
    canvas.addComponentListener(new ComponentAdapter() { //on resize...
      @Override
      public void componentResized(ComponentEvent arg0) {
        width = canvas.getWidth();
        height = canvas.getHeight();
      }
    });

    //set background as per assignment requirement
    canvas.setBackground(Color.darkGray);

    //listen to mouse events
    canvas.addMouseMotionListener(new MouseMoveListener());

    frame.add(canvas, BorderLayout.CENTER);

    //pack to proper size
    frame.pack();

    //center frame on user's screen
    frame.setLocationRelativeTo(null);

    //show the frame
    frame.setVisible(true);

    //set up a buffer strategy used for smooth rendering
    //needs to be called after JFrame is valid and visible
    canvas.createBufferStrategy(2);
    strategy = canvas.getBufferStrategy();
  }

  /** Stops the game loop. */
  public void stop() {
    running = false;
  }

  /** Called to initialize the game loop. */
  public void run() {
    init();

    int frames = 0;
    long lastTime, lastSec;
    lastTime = lastSec = System.nanoTime();
    running = true;
    while (running) {
      long deltaTime = System.nanoTime() - lastTime;
      lastTime += deltaTime;

      //update the game by a little
      update(deltaTime / 1e9);

      //pretty standard buffer strategy game loop
      do {
        do {
          Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
          //clear screen
          g.setColor(Color.white);
          g.clearRect(0, 0, width, height);
          render(g);
          g.dispose();
        } while (strategy.contentsRestored());
        strategy.show();
      } while (strategy.contentsLost());

      //count FPS
      frames++;
      if (System.nanoTime() - lastSec >= 1e9) {
        fps = frames;
        frames = 0;
        lastSec += 1e9;
      }

      //sync frame rate to 60 FPS
      do {
        Thread.yield();
      } while (System.nanoTime() - lastTime < 1e9 / 60);
    }
  }

  /** Called on first run to initialize the game and any resources. */
  protected void init() {
    //add the first entity
    entities.add(new Ellipse2D.Float(125, 125, 50, 50));
    entities.add(new Rectangle2D.Float(125, 225, 20, 20));
  }

  /** Updates the game's entities. */
  protected void update(double deltaTime) {

  }

  /** Called to render the frame. */
  protected void render(Graphics2D g) {
    //we'll use nice quality interpolation
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.setColor(Color.white);
    g.drawString("FPS: " + fps, 10, 20);

    //render the shadows first
    renderShadows(g);

    //render each entity
    for (int i = 0; i < entities.size(); i++) {
      Shape e = entities.get(i);
      g.setColor(Color.WHITE);
      g.fill(e);
    }
  }

  /**
   * Renders the shadows using simple vector math. The steps are as follows:
   * 
   * <pre>
   * for each entity
   *     if entity is not moving:
   *         ignore entity
   *     if entity is too far from mouse:
   *         ignore entity
   *         
   *     determine unit vector from mouse to entity center
   *     get perpendicular of unit vector
   *     
   *     Create Points A + B:
   *         extrude perpendicular in either direction, by the half-size of the entity 
   *     Create Points C + D: 
   *         extrude A + B away from mouse position
   *         
   *     construct polygon with points A, B, C, D
   *     
   *     render with RadialGradientPaint to give it a "fade-out" appearance
   * </pre>
   * 
   * @param g
   *          the graphics to use for rendering
   */
  protected void renderShadows(Graphics2D g) {
    //old Paint object for resetting it later
    Paint oldPaint = g.getPaint();

    //minimum distance (squared) which will save us some checks
    float minDistSq = GRADIENT_SIZE * GRADIENT_SIZE;
    //amount to extrude our shadow polygon by
    //use a large enough value to ensure that it is way off screen
    final float SHADOW_EXTRUDE = GRADIENT_SIZE * GRADIENT_SIZE;

    //we'll use a radial gradient from the mouse center
    final Paint GRADIENT_PAINT = new RadialGradientPaint(new Point2D.Float(mouseX, mouseY),
        GRADIENT_SIZE, GRADIENT_FRACTIONS, GRADIENT_COLORS);

    final Point2D.Float mouse = new Point2D.Float(mouseX, mouseY);

    //for each entity
    for (int i = 0; i < entities.size(); i++) {
      Shape e = entities.get(i);

      Rectangle2D bounds = e.getBounds2D();

      //radius of Entity's bounding circle
      float r = (float) bounds.getWidth() / 2f;

      //get center of entity
      float cx = (float) bounds.getX() + r;
      float cy = (float) bounds.getY() + r;

      //get direction from mouse to entity center
      float dx = cx - mouse.x;
      float dy = cy - mouse.y;

      //get euclidean distance from mouse to center
      float distSq = dx * dx + dy * dy; //avoid sqrt for performance

      //if the entity is outside of the shadow radius, then ignore
      if (distSq > minDistSq)
        continue;

      //normalize the direction to a unit vector
      float len = (float) Math.sqrt(distSq);
      float nx = dx;
      float ny = dy;
      if (len != 0) { //avoid division by 0
        nx /= len;
        ny /= len;
      }

      //get perpendicular of unit vector
      float px = -ny;
      float py = nx;

      //our perpendicular points in either direction from radius
      Point2D.Float A = new Point2D.Float(cx - px * r, cy - py * r);
      Point2D.Float B = new Point2D.Float(cx + px * r, cy + py * r);

      //project the points by our SHADOW_EXTRUDE amount
      Point2D.Float C = project(mouse, A, SHADOW_EXTRUDE);
      Point2D.Float D = project(mouse, B, SHADOW_EXTRUDE);

      //construct a polygon from our points
      POLYGON.reset();
      POLYGON.addPoint((int) A.x, (int) A.y);
      POLYGON.addPoint((int) B.x, (int) B.y);
      POLYGON.addPoint((int) D.x, (int) D.y);
      POLYGON.addPoint((int) C.x, (int) C.y);

      //fill the polygon with the gradient paint
      g.setPaint(GRADIENT_PAINT);
      g.fill(POLYGON);
    }

    //reset to old Paint object
    g.setPaint(oldPaint);
  }

  /**
   * Projects a point from end along the vector (end - start) by the given
   * scalar amount.
   */
  private Point2D.Float project(Point2D.Float start, Point2D.Float end, float scalar) {
    float dx = end.x - start.x;
    float dy = end.y - start.y;
    //euclidean length
    float len = (float) Math.sqrt(dx * dx + dy * dy);
    //normalize to unit vector
    if (len != 0) { //avoid division by 0
      dx /= len;
      dy /= len;
    }
    //multiply by scalar amount
    dx *= scalar;
    dy *= scalar;
    return new Point2D.Float(end.x + dx, end.y + dy);
  }

  /** Mouse motion listener for dynamic 2D shadows. */
  private class MouseMoveListener extends MouseMotionAdapter {

    @Override
    public void mouseMoved(MouseEvent e) {
      mouseX = e.getX();
      mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      mouseX = e.getX();
      mouseY = e.getY();
    }
  }

}