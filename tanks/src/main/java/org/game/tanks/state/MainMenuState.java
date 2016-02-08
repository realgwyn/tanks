package org.game.tanks.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.SpriteName;
import org.game.tanks.core.GameDisplay;
import org.game.tanks.core.GameEngine;
import org.game.tanks.core.ResourceManager;
import org.game.tanks.model.Sprite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuState extends State {

  static final Logger logger = Logger.getLogger(MainMenuState.class);

  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  RoundState roundState;
  @Autowired
  ResourceManager res;

  @Override
  public void onStateBegin() {
    display.requestFocus();
  }

  
  List<Sprite> sprites;
  
  @PostConstruct
  public void init(){
    sprites = new ArrayList<>();
    sprites.add(res.getSprite(SpriteName.TANK_EXPLO));
    sprites.add(res.getSprite(SpriteName.FX_HIT_PUFF));
    sprites.add(res.getSprite(SpriteName.TANK_BODY));
    sprites.add(res.getSprite(SpriteName.TANK_HIT));
    sprites.add(res.getSprite(SpriteName.TANK_TOWER));
    sprites.add(res.getSprite(SpriteName.TANK_TOWER_FIRE));
    sprites.add(res.getSprite(SpriteName.UPGRADE_AMMO));
  }
  
  @Override
  public void update() {
    for(Sprite sprite : sprites){
      sprite.updateTick();
    }
  }

  @Override
  public void draw() {
    

    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, display.WIDTH, display.HEIGHT);
    g.setColor(Color.WHITE);
    g.drawString("Welcome to the Game", 20, 20);
    g.drawString("ENTER - Start Game!", 40, 50);
    g.drawString("ESCAPE - Exit", 40, 70);
    
    for(int i = 0;i<sprites.size();i++){
      display.getGraphics().drawImage(sprites.get(i).getImage(), i * 40, 200, null);
    }
    
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ENTER:
      engine.setState(roundState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.stop();
      break;
    default:
      break;
    }
  }

}
