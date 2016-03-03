package org.game.tanks.client.gui.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.gui.widgets.components.Label;
import org.game.tanks.client.state.CreateGameMenuState;
import org.game.tanks.client.state.FindGameMenuState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class MainMenuWindow extends GuiComponent {

  @Autowired
  GameEngine engine;
  @Autowired
  CreateGameMenuState createGameMenuState;
  @Autowired
  FindGameMenuState findGameMenuState;
  @Autowired
  Config cfg;

  @PostConstruct
  @Override
  public void initialize() {
    width = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(Config.GAME_RESOLUTION_HEIGHT);
    add(new Label("Welcome to the Game"), 20, 20);
    add(new Label("F - Find Servers"), 40, 50);
    add(new Label("C - Create the Game"), 40, 70);
    add(new Label("ESCAPE - Exit"), 40, 90);
    super.initialize();
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.green);
    g.drawRect(x, y, width, height);
    super.draw(g);
  }

  @Override
  public void setVisible(boolean visible) {
    // TODO Auto-generated method stub
    super.setVisible(visible);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_F:
    case KeyEvent.VK_ENTER:
      engine.setState(findGameMenuState);
      break;
    case KeyEvent.VK_C:
      engine.setState(createGameMenuState);
    case KeyEvent.VK_ESCAPE:
      engine.stop();
      break;
    default:
      break;
    }
  }
}
