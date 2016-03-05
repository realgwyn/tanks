package org.game.tanks.client.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.core.GamePhysicsUnit;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.core.PlayerInput;
import org.game.tanks.client.state.ClientState.ClientStateType;
import org.game.tanks.client.view.InGameChatWindow;
import org.game.tanks.client.view.InGameMenuWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundState extends ClientState {
  
  static final Logger logger = Logger.getLogger(RoundState.class);

  @Autowired
  GameDisplay display;
  @Autowired
  PlayerInput input;
  @Autowired
  GamePhysicsUnit gamePhysicsUnit;
  @Autowired
  GameEngine engine;
  @Autowired
  InGameChatWindow chatWindow;
  @Autowired
  InGameMenuWindow menuWindow;
  @Autowired
  GuiManager guiManager;
  
  public RoundState(){
    super(ClientStateType.ROUND);
  }

  @Override
  public void update() {
    gamePhysicsUnit.update();
    //Update other stuff
  }

  @Override
  public void draw() {
    gamePhysicsUnit.draw();
    Graphics g = display.getGraphics();
    g.setColor(Color.WHITE);
    g.drawString("Game Round State", display.WIDTH / 2 - 75, display.HEIGHT / 2 - 10);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()){
    case KeyEvent.VK_ENTER:
      guiManager.showComponent(chatWindow);
      break;
    case KeyEvent.VK_ESCAPE:
      guiManager.showComponent(menuWindow);
    break;
      default:
        break;
    }
  }

}
