package org.game.tanks.state;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.log4j.Logger;

public abstract class State implements KeyListener{
  
  static final Logger logger = Logger.getLogger(State.class);
  
  public abstract void update();

  public abstract void draw();

  public void onStateBegin(){
  }

  public void onStateEnd(){
  }
  
  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

}
