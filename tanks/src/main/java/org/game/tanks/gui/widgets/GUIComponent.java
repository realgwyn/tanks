package org.game.tanks.gui.widgets;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GUIComponent extends Rectangle implements Focusable {

  private static final long serialVersionUID = 2704815453491615138L;

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
  
  public void onFocus(){
  }
  
  public void onFocusLost(){
  }

}
