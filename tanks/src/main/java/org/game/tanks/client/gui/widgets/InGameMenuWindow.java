package org.game.tanks.client.gui.widgets;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.state.MainMenuState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class InGameMenuWindow extends GuiComponent {

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  GuiManager guiManager;

  @PostConstruct
  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
  }

  @Override
  public void draw(Graphics g) {
    // g.setColor(Color.BLACK);
    // g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 40, 160, 80);
    // g.setColor(Color.WHITE);
    // g.drawString("Game Menu", display.WIDTH / 2 - 75, display.HEIGHT / 2 - 10);
    // g.drawString(" Exit - E", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 5);
    // g.drawString(" Resume - ESC", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 20);
    super.draw(g);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_E:
      engine.setState(mainMenuState);
      break;
    case KeyEvent.VK_ESCAPE:
      guiManager.hideComponent(this);
      break;
    default:
      break;
    }
  }

}
