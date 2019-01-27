package io.game.tanks.client.state.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.client.core.GameDisplay;
import io.game.tanks.client.core.GuiManager;
import io.game.tanks.client.state.ClientState;
import io.game.tanks.client.view.menu.CreateLanGameMenuWindow;
import io.game.tanks.network.state.ClientStateType;

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
