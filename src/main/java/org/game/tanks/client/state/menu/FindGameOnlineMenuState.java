package org.game.tanks.client.state.menu;

import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.state.ClientState;
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

  private int animationCounter;

  public FindGameOnlineMenuState() {
    super(ClientStateType.FIND_GAME_ONLINE_MENU);
  }

  @Override
  public void onStateBegin() {
    guiManager.showAndFocusComponent(findGameMenuWindow);
  }

  @Override
  public void update() {
    animationCounter++;
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = (int) (i * -1 * animationCounter * 1.34);
    }
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}
