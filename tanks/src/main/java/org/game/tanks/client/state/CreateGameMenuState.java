package org.game.tanks.client.state;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.gui.widgets.CreateGameMenuWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateGameMenuState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  CreateGameMenuWindow createGameMenuWindow;

  public CreateGameMenuState() {
    super(ClientStateType.CREATE_GAME_MENU);
  }

  @Override
  public void onStateBegin() {
    guiManager.showAndFocusComponent(createGameMenuWindow);
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
    g.drawString("CreateGameMenuState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

}
