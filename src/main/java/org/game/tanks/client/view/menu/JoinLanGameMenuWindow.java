package org.game.tanks.client.view.menu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.cfg.GameStyle;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.state.MatchInitState;
import org.game.tanks.client.state.menu.MainMenuState;
import org.game.tanks.client.view.GuiComponent;
import org.game.tanks.client.view.components.Button;
import org.game.tanks.client.view.components.Label;
import org.game.tanks.client.view.components.Label.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class JoinLanGameMenuWindow extends GuiComponent {

  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  MatchInitState matchInitState;
  @Autowired
  Config cfg;

  private Button btnJoinGame;
  private Button btnCancel;
  private Label lblServerStatus;
  private Label lblServerStatusValue;

  private boolean serverOnline;

  @PostConstruct
  public void initialize() {
    initComponents();
    initActions();
  }

  private void initComponents() {
    width = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(Config.GAME_RESOLUTION_HEIGHT);
    Label label = new Label("Join Local Area Network Game", GameStyle.FONT_BIG_MESSAGE);
    label.setHorizontalAlignment(HorizontalAlignment.CENTER);
    add(label, width / 2, 20);
    lblServerStatus = new Label("Server: ", GameStyle.FONT_MENU_BUTTON);
    add(lblServerStatus, 20, 80);
    lblServerStatusValue = new Label("Offline", GameStyle.FONT_MENU_BUTTON);
    lblServerStatusValue.setForegroundColor(GameStyle.RED);
    add(lblServerStatusValue, 100, 80);
    btnJoinGame = new Button("ENTER - Join LAN Game", GameStyle.FONT_MENU_BUTTON);
    btnJoinGame.setBorderEnabled(false);
    add(btnJoinGame, 20, 100);
    btnJoinGame.setEnabled(false);
    btnCancel = new Button("ESC - Cancel", GameStyle.FONT_MENU_BUTTON);
    btnCancel.setBorderEnabled(false);
    add(btnCancel, 20, height - 40);

  }

  private void initActions() {
    btnJoinGame.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(matchInitState);
      }
    });
    btnCancel.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(mainMenuState);
      }
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ENTER:
      if (serverOnline) {
        engine.setState(matchInitState);
      }
      break;
    case KeyEvent.VK_ESCAPE:
      engine.setState(mainMenuState);
      break;
    default:
      break;
    }
  }

  public void setServerOnline(boolean online) {
    if (online) {
      serverOnline = online;
      lblServerStatusValue.setText("Online");
      lblServerStatusValue.setForegroundColor(GameStyle.GREEN);
      btnJoinGame.setEnabled(true);
    } else {
      serverOnline = online;
      lblServerStatusValue.setText("Offline");
      lblServerStatusValue.setForegroundColor(GameStyle.RED);
      btnJoinGame.setEnabled(false);
    }

  }
}
