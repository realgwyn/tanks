package org.game.tanks.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.game.tanks.core.GameDisplay;
import org.game.tanks.core.GameEngine;
import org.game.tanks.core.LogicUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundChatState extends State {

  static final Logger logger = Logger.getLogger(RoundChatState.class);

  @Autowired
  GameEngine engine;
  @Autowired
  RoundState roundState;
  @Autowired
  GameDisplay display;
  @Autowired
  LogicUnit logicUnit;

  @Override
  public void update() {
    logicUnit.update();
  }

  @Override
  public void draw() {
    logicUnit.draw();
    Graphics g = display.getGraphics();
    g.setColor(Color.black);
    g.fillRect(10, display.HEIGHT - 60, display.WIDTH - 20, 50);
    g.setColor(Color.green);
    g.drawRect(10, display.HEIGHT - 60, display.WIDTH - 20, 50);
    g.drawString("Write Chat:", 20, display.HEIGHT - 45);
  }

  @Override
  public void onStateBegin() {

  }

  @Override
  public void onStateEnd() {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ENTER:
      sendChatMessage("wololo");
      engine.setState(roundState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.setState(roundState);
      break;
    default:

      break;
    }
  }

  private void sendChatMessage(String chatMessage) {
    System.out.println("Sending chat message: " + chatMessage);
  }

}
