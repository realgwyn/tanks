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
public class RoundMenuState extends State{
  
  static final Logger logger = Logger.getLogger(RoundMenuState.class);

  @Autowired
  RoundState roundState;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
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
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 40,  160,  80);
    g.setColor(Color.WHITE);
    g.drawString("Game Menu", display.WIDTH / 2 - 75, display.HEIGHT / 2 - 10);
    g.drawString(" Exit - E", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 5);
    g.drawString(" Resume - ESC", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 20);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_E:
      engine.setState(mainMenuState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.setState(roundState);
      break;
    default:
      break;
    }
  }

}
