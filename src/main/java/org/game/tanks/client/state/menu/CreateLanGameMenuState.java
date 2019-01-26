package org.game.tanks.client.state.menu;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.state.ClientState;
import org.game.tanks.client.view.menu.CreateLanGameMenuWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateLanGameMenuState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  CreateLanGameMenuWindow createGameMenuWindow;

  private int animationCounter;

  public CreateLanGameMenuState() {
    super(ClientStateType.CREATE_LAN_GAME_MENU);
  }

  @Override
  public void onStateBegin() {
    guiManager.showAndFocusComponent(createGameMenuWindow);
  }

  @Override
  public void update() {
    animationCounter++;
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = (int) (i * animationCounter * 1.34);
    }
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}
