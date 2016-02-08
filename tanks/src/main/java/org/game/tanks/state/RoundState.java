package org.game.tanks.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.game.tanks.core.GameDisplay;
import org.game.tanks.core.GameEngine;
import org.game.tanks.core.LogicUnit;
import org.game.tanks.core.PlayerInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundState extends State {
  
  static final Logger logger = Logger.getLogger(RoundState.class);

  @Autowired
  GameDisplay display;
  @Autowired
  PlayerInput input;
  @Autowired
  LogicUnit logicUnit;
  @Autowired
  GameEngine engine;
  @Autowired
  RoundChatState chatState;
  @Autowired
  RoundMenuState roundMenuState;

  public void update() {
    logicUnit.update();
    //Update other stuff
  }

  public void draw() {
    logicUnit.draw();
    Graphics g = display.getGraphics();
    g.setColor(Color.WHITE);
    g.drawString("Game Round State", display.WIDTH / 2 - 75, display.HEIGHT / 2 - 10);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()){
    case KeyEvent.VK_ENTER:
      engine.setState(chatState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.setState(roundMenuState);
    break;
      default:
        break;
    }
  }

}
