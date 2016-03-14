package org.game.tanks.client.view.menu;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.cfg.GameStyle;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.state.menu.CreateLanGameMenuState;
import org.game.tanks.client.state.menu.FindGameOnlineMenuState;
import org.game.tanks.client.state.menu.JoinLanGameMenuState;
import org.game.tanks.client.state.menu.OptionsMenuState;
import org.game.tanks.client.view.GuiComponent;
import org.game.tanks.client.view.components.Button;
import org.game.tanks.client.view.components.Label;
import org.game.tanks.client.view.components.Label.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class MainMenuWindow extends GuiComponent {

  @Autowired
  GameEngine engine;
  @Autowired
  CreateLanGameMenuState createGameMenuState;
  @Autowired
  FindGameOnlineMenuState findGameMenuState;
  @Autowired
  OptionsMenuState optionsMenuState;
  @Autowired
  JoinLanGameMenuState joinLanGameMenuState;
  @Autowired
  Config cfg;

  private Button btnFindGameOnline;
  private Button btnJoinLanGame;
  private Button btnCreateLanGame;
  private Button btnOptions;
  private Button btnExit;

  @PostConstruct
  public void initialize() {
    initComponents();
    initActions();
  }

  private void initComponents() {
    width = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(Config.GAME_RESOLUTION_HEIGHT);
    Label label = new Label("Welcome to the Game", GameStyle.FONT_BIG_MESSAGE);
    label.setHorizontalAlignment(HorizontalAlignment.CENTER);
    add(label, width / 2, 60);
    btnFindGameOnline = new Button("F - Find Game Online", GameStyle.FONT_MENU_BUTTON);
    add(btnFindGameOnline, 40, 80);
    btnJoinLanGame = new Button("J - Join LAN Game", GameStyle.FONT_MENU_BUTTON);
    add(btnJoinLanGame, 40, 100);
    btnCreateLanGame = new Button("C - Create LAN Game", GameStyle.FONT_MENU_BUTTON);
    add(btnCreateLanGame, 40, 120);
    btnOptions = new Button("O - Options", GameStyle.FONT_MENU_BUTTON);
    add(btnOptions, 40, 140);
    btnExit = new Button("ESC - Exit", GameStyle.FONT_MENU_BUTTON);
    add(btnExit, 40, 160);
  }

  public void initActions() {
    btnFindGameOnline.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(findGameMenuState);
      }
    });
    btnJoinLanGame.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(joinLanGameMenuState);
      }
    });
    btnCreateLanGame.setMouseActionListener(new MouseAdapter() {
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
    btnExit.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.stop();
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
    case KeyEvent.VK_J:
      engine.setState(joinLanGameMenuState);
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
