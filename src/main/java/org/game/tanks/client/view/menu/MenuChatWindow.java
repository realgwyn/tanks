package org.game.tanks.client.view.menu;


import java.awt.*;

import javax.annotation.PostConstruct;

import org.game.tanks.client.view.GuiComponent;
import org.springframework.stereotype.Component;

/**
 * Main Menu Chat window for talking with friends
 */
@Component
@SuppressWarnings("serial")
public class MenuChatWindow extends GuiComponent {

  @PostConstruct
  public void initialize() {
    // TODO Auto-generated method stub
  }

  @Override
  public void paintComponent(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, width, height);
    g.setColor(Color.green);
    g.drawRect(x, y, width, height);
    super.paintComponent(g);
  }

}
