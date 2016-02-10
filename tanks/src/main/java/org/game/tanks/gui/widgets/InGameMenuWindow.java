package org.game.tanks.gui.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.game.tanks.core.GameDisplay;
import org.game.tanks.core.GameEngine;
import org.game.tanks.core.GuiManager;
import org.game.tanks.state.MainMenuState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InGameMenuWindow extends GuiComponent{

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  GuiManager guiManager;

  @Override
  public void draw() {
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
      guiManager.removeAllComponents();
      engine.setState(mainMenuState);
      break;
    case KeyEvent.VK_ESCAPE:
      guiManager.removeComponent(this);
      break;
    default:
      break;
    }
  }

}
