package org.game.tanks.client.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.game.tanks.client.gui.widgets.Focusable;

public abstract class ClientState implements Focusable {

  static final Logger logger = Logger.getLogger(ClientState.class);
  
  public enum ClientStateType {GAME_END, GAME_LOAD, GAME_PENDING, GAME_START, STARTUP, MAIN_MENU, ROUND_END, ROUND_START, ROUND}
  private ClientStateType type;
  
  public ClientState(ClientStateType type){
    this.type = type;
  }

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

  public ClientStateType getType() {
    return type;
  }
  
}
