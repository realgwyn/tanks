package org.game.tanks.client.state;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.view.HudWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Waiting for other players at the beginning of the match
 */
@Component
public class MatchStartState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  HudWindow hudWindow;

  public MatchStartState() {
    super(ClientStateType.MATCH_START);
  }

  @Override
  public void onStateBegin() {
    guiManager.showComponent(hudWindow);
  }

  @Override
  public void update() {
    // TODO: scores will not be counted
  }

  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20, 160, 40);
    g.setColor(Color.WHITE);
    g.drawString("MatchStartState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

}
