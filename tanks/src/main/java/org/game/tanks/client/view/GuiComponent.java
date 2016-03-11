package org.game.tanks.client.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

  protected boolean visible = true;
  protected boolean focusable = true;

  private List<GuiComponent> children;

  private MouseListener mouseActionListener;

  private int relativeX;
  private int relativeY;

  public void setParent(GuiComponent parent) {
    this.parent = parent;
  }

  public void add(GuiComponent child, int relativeX, int relativeY) {
    child.relativeX = relativeX;
    child.relativeY = relativeY;
    child.setParent(this);
    child.setLocation(getAbsoluteX(relativeX), getAbsoluteY(relativeY));
    if (children == null) {
      children = new ArrayList<>();
    }
    children.add(child);
  }

  private int getAbsoluteX(int relativeX) {
    if (parent != null) {
      return (int) parent.getX() + relativeX;
    }
    return relativeX;
  }

  private int getAbsoluteY(int relativeY) {
    if (parent != null) {
      return (int) parent.getY() + relativeY;
    }
    return relativeY;
  }

  @Override
  public void setLocation(int newX, int newY) {
    this.x = newX;
    this.y = newY;
    if (children != null) {
      for (GuiComponent child : children) {
        child.setLocation(newX + child.getRelativeX(), newY + child.getRelativeY());
      }
    }
  }

  public int getRelativeX() {
    return relativeX;
  }

  public int getRelativeY() {
    return relativeY;
  }

  @Override
  public void setSize(int newWidth, int newHeight) {
    this.width = newWidth;
    this.height = newHeight;
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

  public void paintComponent(Graphics g) {
    if (children != null) {
      for (GuiComponent child : children) {
        if (child.isVisible()) {
          child.paintComponent(g);
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
    if (children != null && !children.isEmpty()) {
      for (GuiComponent child : children) {
        if (child.contains(e.getX(), e.getY())) {
          child.mousePressed(e);
          return;
        }
      }
    }

    if (mouseActionListener != null) {
      mouseActionListener.mousePressed(e);
    }
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

  public boolean isFocusable() {
    return focusable;
  }

  public void setFocusable(boolean focusable) {
    this.focusable = focusable;
  }

  /**
   * Update GuiComponent values (Busy Spinner, HUD Panel, MapPanel)
   */
  public void update() {
  }

  public void setMouseActionListener(MouseListener a) {
    this.mouseActionListener = a;
  }

  @Override
  @Deprecated
  public void add(Rectangle r) {
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    GuiComponent other = (GuiComponent) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
