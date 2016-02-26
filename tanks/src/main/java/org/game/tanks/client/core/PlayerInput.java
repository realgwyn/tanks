package org.game.tanks.client.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import org.game.tanks.client.gui.widgets.Focusable;
import org.springframework.stereotype.Component;

@Component
public class PlayerInput implements KeyListener, MouseListener, MouseMotionListener {

  private boolean[] keyFlags = new boolean[10];
  Focusable inputListener;

  private Map<Integer, Integer> keyboardBinding;
  private Map<Integer, Integer> mouseBinding;
  
  private int mouseX;
  private int mouseY;

  public PlayerInput() {
    keyboardBinding = new HashMap<Integer, Integer>();
    keyboardBinding.put(KeyEvent.VK_W, GameButtons.UP);
    keyboardBinding.put(KeyEvent.VK_S, GameButtons.DOWN);
    keyboardBinding.put(KeyEvent.VK_A, GameButtons.LEFT);
    keyboardBinding.put(KeyEvent.VK_D, GameButtons.RIGHT);
    keyboardBinding.put(KeyEvent.VK_SPACE, GameButtons.BREAK);
    keyboardBinding.put(KeyEvent.VK_ENTER, GameButtons.ENTER);

    mouseBinding = new HashMap<Integer, Integer>();
    mouseBinding.put(MouseEvent.BUTTON1, GameButtons.MOUSE_LEFT);
    mouseBinding.put(MouseEvent.BUTTON2, GameButtons.MOUSE_MIDDLE);
    mouseBinding.put(MouseEvent.BUTTON3, GameButtons.MOUSE_RIGHT);
  }

  public void setInputListener(Focusable listener) {
    this.inputListener = listener;
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    Integer key = keyboardBinding.get(keyEvent.getKeyCode());
    if (key != null) {
      keyFlags[key] = true;
    }
    if (inputListener != null) {
      inputListener.keyPressed(keyEvent);
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    Integer key = keyboardBinding.get(keyEvent.getKeyCode());
    if (key != null) {
      keyFlags[key] = false;
    }
    if (inputListener != null) {
      inputListener.keyReleased(keyEvent);
    }
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    keyFlags[mouseBinding.get(mouseEvent.getButton())] = true;
    inputListener.mousePressed(mouseEvent);
  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {
    keyFlags[mouseBinding.get(mouseEvent.getButton())] = false;
    inputListener.mouseReleased(mouseEvent);
  }

  @Override
  public void mouseClicked(MouseEvent paramMouseEvent) {
  }

  @Override
  public void mouseEntered(MouseEvent paramMouseEvent) {
  }

  @Override
  public void mouseExited(MouseEvent paramMouseEvent) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
    inputListener.mouseMoved(e);
  }
  
  public boolean[] getKeyFlags() {
    return keyFlags;
  }
  
  public int getMouseX(){
    return mouseX;
  }

  public int getMouseY(){
    return mouseY;
  }

}
