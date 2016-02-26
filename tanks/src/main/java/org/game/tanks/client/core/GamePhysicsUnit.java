package org.game.tanks.client.core;

import org.game.tanks.cfg.EngineConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamePhysicsUnit {

  @Autowired
  EngineConstants gc;
  @Autowired
  GameDisplay display;
  @Autowired
  GameContext gameContext;

  public void generateConstantsValues() {
    // TODO Auto-generated method stub
  }

  private int count;

  public void update() {
    count++;
  }

  public void draw() {

    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = ((i * count));
    }
  }

}