package io.tanks.client.view;


import java.awt.*;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import io.tanks.client.core.GameDisplay;
import io.tanks.client.core.GameEngine;
import io.tanks.client.core.GuiManager;
import io.tanks.client.state.menu.MainMenuState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class MenuWindow extends GuiComponent {

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  GuiManager guiManager;

  @PostConstruct
  public void initialize() {
    // TODO Auto-generated method stub
  }

  @Override
  public void paintComponent(Graphics g) {
    // g.setColor(Color.BLACK);
    // g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 40, 160, 80);
    // g.setColor(Color.WHITE);
    // g.drawString("Game Menu", display.WIDTH / 2 - 75, display.HEIGHT / 2 - 10);
    // g.drawString(" Exit - E", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 5);
    // g.drawString(" Resume - ESC", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 20);
    super.paintComponent(g);
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
