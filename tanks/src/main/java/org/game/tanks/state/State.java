package org.game.tanks.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.game.tanks.gui.widgets.Focusable;

public abstract class State implements Focusable {

  static final Logger logger = Logger.getLogger(State.class);

  public abstract void update();

  public abstract void draw();

  public void onStateBegin() {
  }

  public void onStateEnd() {
  }

  public void onFocus() {
  }

  public void onFocusLost() {
  }

  public void keyPressed(KeyEvent e) {
  }

  public void keyReleased(KeyEvent e) {
  }

  public void mouseMoved(MouseEvent e) {
  }

  public void mouseDragged(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

}
