package org.game.tanks.client.state;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.state.ClientState.ClientStateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundStartState extends ClientState {

  @Autowired
  GameDisplay display;
  
  public RoundStartState(){
    super(ClientStateType.ROUND_START);
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
    g.drawString("RoundEndState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }
}