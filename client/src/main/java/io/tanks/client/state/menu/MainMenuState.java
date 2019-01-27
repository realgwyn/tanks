package io.tanks.client.state.menu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.core.GameDisplay;
import io.tanks.client.core.GameEngine;
import io.tanks.client.core.GuiManager;
import io.tanks.client.state.ClientState;
import io.tanks.client.view.menu.MainMenuWindow;
import io.tanks.common.network.state.ClientStateType;

@Component
public class MainMenuState extends ClientState {

  static final Logger logger = Logger.getLogger(MainMenuState.class);

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  GuiManager guiManager;
  @Autowired
  MainMenuWindow mainMenuWindow;

  private int animationCounter;

  public MainMenuState() {
    super(ClientStateType.MAIN_MENU);
  }

  @Override
  public void onStateBegin() {
    display.requestFocus();
    guiManager.showAndFocusComponent(mainMenuWindow);
  }

  @Override
  public void update() {
    animationCounter++;
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = animationCounter + i / 2;
    }
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}
