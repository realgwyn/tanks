package org.game.tanks.state;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.core.GameDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameLoadState extends State {

  @Autowired
  GameDisplay display;
  
  public GameLoadState(){
    super(StateType.GAME_LOAD);
  }

  @Override
  public void update() {

  }

  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20, 160, 40);
    g.setColor(Color.WHITE);
    g.drawString("GameLoadState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }
}

