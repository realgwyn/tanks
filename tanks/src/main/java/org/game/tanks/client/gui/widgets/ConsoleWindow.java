package org.game.tanks.client.gui.widgets;

import java.awt.Color;
import java.awt.Graphics;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class ConsoleWindow extends GuiComponent {

  @PostConstruct
  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, width, height);
    g.setColor(Color.green);
    g.drawRect(x, y, width, height);
    super.draw(g);
  }

  @Override
  public void setVisible(boolean visible) {
    // TODO Auto-generated method stub
    super.setVisible(visible);
  }

}
