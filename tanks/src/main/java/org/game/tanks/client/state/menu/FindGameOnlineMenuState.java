package org.game.tanks.client.state.menu;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.state.ClientState;
import org.game.tanks.client.state.ClientState.ClientStateType;
import org.game.tanks.client.view.menu.FindGameOnlineMenuWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindGameOnlineMenuState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  FindGameOnlineMenuWindow findGameMenuWindow;

  public FindGameOnlineMenuState() {
    super(ClientStateType.FIND_GAME_ONLINE_MENU);
  }

  @Override
  public void onStateBegin() {
    guiManager.showAndFocusComponent(findGameMenuWindow);
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
    g.drawString("FindGameMenuState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}
