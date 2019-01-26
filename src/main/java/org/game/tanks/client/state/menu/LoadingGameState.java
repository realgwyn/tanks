package org.game.tanks.client.state.menu;


import java.awt.*;

import org.apache.log4j.Logger;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.state.ClientState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingGameState extends ClientState {

  final static Logger logger = Logger.getLogger(LoadingGameState.class);

  private int count;
  private int percent = 0;

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;

  public LoadingGameState() {
    super(ClientStateType.LOADING_GAME);
  }

  @Override
  public void onStateBegin() {
    display.requestFocus();
  }

  @Override
  public void update() {
    count++;
    if (count <= 10) {
      percent = count;
    }
    if (count >= 15) {
      engine.setState(mainMenuState);
    }
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = (int) ((i * count * i * i) / 3.14 * i);
    }

    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20, 160, 40);
    g.setColor(Color.WHITE);
    g.drawString("Loading: " + percent + " %", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }

}
