package org.game.tanks.client.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.SpriteName;
import org.game.tanks.client.model.Sprite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamePhysicsUnit {

  @Autowired
  GameDisplay display;
  @Autowired
  GameContext gameContext;
  @Autowired
  ResourceManager res;

  List<Sprite> sprites;

  @PostConstruct
  public void init() {
    sprites = new ArrayList<>();
    sprites.add(res.getSprite(SpriteName.TANK_EXPLO));
    sprites.add(res.getSprite(SpriteName.FX_HIT_PUFF));
    sprites.add(res.getSprite(SpriteName.TANK_BODY));
    sprites.add(res.getSprite(SpriteName.TANK_HIT));
    sprites.add(res.getSprite(SpriteName.TANK_TOWER));
    sprites.add(res.getSprite(SpriteName.TANK_TOWER_FIRE));
    sprites.add(res.getSprite(SpriteName.UPGRADE_AMMO));
  }

  public void generateConstantsValues() {
    // TODO Auto-generated method stub
  }

  private int count;

  public void update() {
    for (Sprite sprite : sprites) {
      sprite.updateTick();
    }
    count++;
  }

  public void draw() {

    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = ((i * count));
    }

    for (int i = 0; i < sprites.size(); i++) {
      display.getGraphics().drawImage(sprites.get(i).getImage(), i * 40, 200, null);
    }
  }

}