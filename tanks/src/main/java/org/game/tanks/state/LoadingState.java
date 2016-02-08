package org.game.tanks.state;

import java.awt.Color;
import java.awt.Graphics;

import org.apache.log4j.Logger;
import org.game.tanks.core.GameDisplay;
import org.game.tanks.core.GameEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingState extends State {

  final static Logger logger = Logger.getLogger(LoadingState.class);

  private int count;
  private int percent=0;

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;

  @Override
  public void update() {
    count++;
    if(count <= 100){
      percent = count;
    }
    if(count >= 150){
      engine.setState(mainMenuState);
    }
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = (int) ((i * count * i * i)/ 3.14 * i);
    }

    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20,  160,  40);
    g.setColor(Color.WHITE);
    g.drawString("Loading: " + percent + " %", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

}
