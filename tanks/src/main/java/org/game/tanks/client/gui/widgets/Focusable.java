package org.game.tanks.client.gui.widgets;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Focusable {
  
  void keyPressed(KeyEvent e);

  void keyReleased(KeyEvent e);

  void mouseMoved(MouseEvent e);

  void mouseDragged(MouseEvent e);

  void mousePressed(MouseEvent e);

  void mouseReleased(MouseEvent e);

  void onFocusLost();

  void onFocus();

}
