package io.game.tanks.client.state;


import java.awt.*;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.client.core.GameDisplay;
import io.game.tanks.client.core.GameEngine;
import io.game.tanks.client.core.GamePhysicsUnit;
import io.game.tanks.client.core.GuiManager;
import io.game.tanks.client.core.PlayerInput;
import io.game.tanks.client.view.ChatWindow;
import io.game.tanks.client.view.MenuWindow;
import io.game.tanks.network.state.ClientStateType;

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
  ChatWindow chatWindow;
  @Autowired
  MenuWindow menuWindow;
  @Autowired
  GuiManager guiManager;

  public RoundState() {
    super(ClientStateType.ROUND);
  }

  @Override
  public void update() {
    gamePhysicsUnit.update();
    // Update other stuff
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
    switch (e.getKeyCode()) {
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

  @Override
  public void onStateBegin() {
    // TODO Auto-generated method stub

  }

  @Override
  public void onStateEnd() {
    // TODO Auto-generated method stub

  }

}
