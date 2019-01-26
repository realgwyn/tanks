package org.game.tanks.client.view;


import java.awt.*;

import javax.annotation.PostConstruct;

import org.game.tanks.client.view.components.BusySpinner;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class MatchInitWindow extends GuiComponent {

  private BusySpinner busySpinner;

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