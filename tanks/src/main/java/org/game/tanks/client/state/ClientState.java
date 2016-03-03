package org.game.tanks.client.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.game.tanks.client.gui.widgets.Focusable;

public abstract class ClientState implements Focusable {

  static final Logger logger = Logger.getLogger(ClientState.class);

  public enum ClientStateType {
    LOADING_GAME, MAIN_MENU, CREATE_GAME_MENU, FIND_GAME_MENU, MATCH_END, MATCH_INIT, GAME_PENDING, MATCH_START, ROUND_END, ROUND_START, ROUND
  }

  private ClientStateType type;

  public ClientState(ClientStateType type) {
    this.type = type;
  }

  public abstract void update();

  public abstract void draw();

  public void onStateBegin() {
  }

  public void onStateEnd() {
  }

  @Override
  public void onFocus() {
  }

  @Override
  public void onFocusLost() {
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

  public ClientStateType getType() {
    return type;
  }

}
