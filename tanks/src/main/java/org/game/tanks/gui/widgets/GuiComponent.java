package org.game.tanks.gui.widgets;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public abstract class GuiComponent extends Rectangle implements Focusable {
  
  private boolean focused;
  private boolean visible;

  public boolean isFocused() {
    return focused;
  }

  public void setFocused(boolean focused) {
    this.focused = focused;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public abstract void draw();

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
