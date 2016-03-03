package org.game.tanks.client.gui.widgets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("serial")
public abstract class GuiComponent extends Rectangle implements Focusable {

  protected UUID id;

  public GuiComponent() {
    id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  protected GuiComponent parent;

  protected boolean visible;

  private List<GuiComponent> children;

  public void setParent(GuiComponent parent) {
    this.parent = parent;
  }

  public void add(GuiComponent child, int relativeX, int relativeY) {
    child.setLocation((int) parent.getX() + relativeX, (int) parent.getY() + relativeY);
    if (children == null) {
      children = new ArrayList<>();
    }
    child.setParent(this);
    children.add(child);
  }

  public void setLocation() {

  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
    if (children != null) {
      for (GuiComponent child : children) {
        child.setVisible(visible);
      }
    }
  }

  public void initialize() {
    if (children != null) {
      for (GuiComponent child : children) {
        child.initialize();
      }
    }
  }

  public void draw(Graphics g) {
    if (children != null) {
      for (GuiComponent child : children) {
        if (child.isVisible()) {
          child.draw(g);
        }
      }
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void onFocus() {
  }

  @Override
  public void onFocusLost() {
  }

}
