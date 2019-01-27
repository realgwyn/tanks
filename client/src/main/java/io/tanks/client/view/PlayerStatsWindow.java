package io.tanks.client.view;


import java.awt.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class PlayerStatsWindow extends GuiComponent {

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
