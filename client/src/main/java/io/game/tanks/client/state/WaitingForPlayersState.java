package io.game.tanks.client.state;


import java.awt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.client.core.GameDisplay;
import io.game.tanks.client.core.GuiManager;
import io.game.tanks.client.view.HudWindow;
import io.game.tanks.network.state.ClientStateType;

/**
 * Waiting for other players at the beginning of the match
 */
@Component
public class WaitingForPlayersState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  HudWindow hudWindow;

  public WaitingForPlayersState() {
    super(ClientStateType.WAITING_FOR_PLAYERS);
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

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }

}
