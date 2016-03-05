package org.game.tanks.client.view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.ActionManager;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.gui.widgets.components.Button;
import org.game.tanks.client.gui.widgets.components.Label;
import org.game.tanks.client.state.CreateGameMenuState;
import org.game.tanks.client.state.FindGameMenuState;
import org.game.tanks.client.state.OptionsMenuState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class MainMenuWindow extends GuiComponent {

  @Autowired
  ActionManager actionManager;
  @Autowired
  GameEngine engine;
  @Autowired
  CreateGameMenuState createGameMenuState;
  @Autowired
  FindGameMenuState findGameMenuState;
  @Autowired
  OptionsMenuState optionsMenuState;
  @Autowired
  Config cfg;

  private Button btnJoinGame;
  private Button btnCreateGame;
  private Button btnOptions;

  @PostConstruct
  public void initialize() {

    initComponents();
    initActions();
  }

  private void initComponents() {
    width = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(Config.GAME_RESOLUTION_HEIGHT);
    add(new Label("Welcome to the Game"), 20, 20);
    btnJoinGame = new Button("F - Join Game");
    add(btnJoinGame, 40, 50);
    btnCreateGame = new Button("C - Create the Game");
    add(btnCreateGame, 40, 70);
    btnOptions = new Button("O - Options");
    add(btnOptions, 40, 90);
    add(new Button("ESCAPE - Exit"), 40, 110);
  }

  public void initActions() {
    btnJoinGame.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(findGameMenuState);
      }
    });
    btnCreateGame.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(createGameMenuState);
      }
    });
    btnOptions.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(optionsMenuState);
      }
    });
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_F:
    case KeyEvent.VK_ENTER:
      engine.setState(findGameMenuState);
      break;
    case KeyEvent.VK_C:
      engine.setState(createGameMenuState);
      break;
    case KeyEvent.VK_O:
      engine.setState(optionsMenuState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.stop();
      break;
    default:
      break;
    }
  }
}
