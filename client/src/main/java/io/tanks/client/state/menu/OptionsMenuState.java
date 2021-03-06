package io.tanks.client.state.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.core.GameDisplay;
import io.tanks.client.core.GuiManager;
import io.tanks.client.state.ClientState;
import io.tanks.client.view.menu.OptionsMenuWindow;
import io.tanks.common.network.state.ClientStateType;

@Component
public class OptionsMenuState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  OptionsMenuWindow optionsMenuWindow;

  private int animationCounter;

  public OptionsMenuState() {
    super(ClientStateType.OPTIONS_MENU);
  }

  @Override
  public void onStateBegin() {
    guiManager.showAndFocusComponent(optionsMenuWindow);
  }

  @Override
  public void update() {
    animationCounter++;
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = ((int) (i * 0.001));
    }
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}
