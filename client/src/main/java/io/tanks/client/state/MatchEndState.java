package io.tanks.client.state;


import java.awt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.core.GameDisplay;
import io.tanks.client.core.GuiManager;
import io.tanks.common.network.state.ClientStateType;

/**
 * Display End Match Players Score
 */
@Component
public class MatchEndState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;

  public MatchEndState() {
    super(ClientStateType.MATCH_END);
  }

  @Override
  public void update() {

    // TODO: Show statswindow
    // TODO: show which team has won

  }

  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20, 160, 40);
    g.setColor(Color.WHITE);
    g.drawString("MatchEndState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

  @Override
  public void onStateBegin() {
    // TODO Auto-generated method stub

  }

}
