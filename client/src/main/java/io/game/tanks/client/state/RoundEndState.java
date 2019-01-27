package io.game.tanks.client.state;


import java.awt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.client.core.GameDisplay;
import io.game.tanks.network.state.ClientStateType;

@Component
public class RoundEndState extends ClientState {

  @Autowired
  GameDisplay display;

  public RoundEndState() {
    super(ClientStateType.ROUND_END);
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

  @Override
  public void onStateBegin() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }
}
