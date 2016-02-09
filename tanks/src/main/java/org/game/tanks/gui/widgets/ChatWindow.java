package org.game.tanks.gui.widgets;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.core.GameDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatWindow extends GUIComponent{

  @Autowired
  GameDisplay display;
  
  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.black);
    g.fillRect(10, display.HEIGHT - 60, display.WIDTH - 20, 50);
    g.setColor(Color.green);
    g.drawRect(10, display.HEIGHT - 60, display.WIDTH - 20, 50);
    g.drawString("Write Chat:", 20, display.HEIGHT - 45);
  }

}
