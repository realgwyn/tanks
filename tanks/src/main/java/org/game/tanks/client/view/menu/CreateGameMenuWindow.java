package org.game.tanks.client.view.menu;

import java.awt.Color;
import java.awt.Graphics;

import javax.annotation.PostConstruct;

import org.game.tanks.client.view.GuiComponent;
import org.springframework.stereotype.Component;

/**
 *  
 */
@Component
@SuppressWarnings("serial")
public class CreateGameMenuWindow extends GuiComponent {

  @PostConstruct
  public void initialize() {
    // TODO Auto-generated method stub
  }

  @Override
  public void paintComponent(Graphics g) {

    g.setColor(Color.green);
    g.drawRect(x, y, width, height);
    super.paintComponent(g);
  }

}