package org.game.tanks.client.view.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.state.MatchInitState;
import org.game.tanks.client.state.menu.MainMenuState;
import org.game.tanks.client.view.GuiComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class FindGameOnlineMenuWindow extends GuiComponent {

  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  MatchInitState matchInitState;

  @PostConstruct
  public void initialize() {
    // TODO Auto-generated method stub
  }

  @Override
  public void paintComponent(Graphics g) {
    g.drawString("find game menu window", 10, 10);
    super.paintComponent(g);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ENTER:
      engine.setState(matchInitState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.setState(mainMenuState);
      break;
    default:
      break;
    }
  }

}
