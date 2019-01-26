package org.game.tanks.client.core;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface PlayerInputListener {

  void keyPressed(KeyEvent e);

  void keyReleased(KeyEvent e);

  void mouseMoved(MouseEvent e);

  void mouseDragged(MouseEvent e);

  void mousePressed(MouseEvent e);

  void mouseReleased(MouseEvent e);

}
