package io.tanks.client.view.menu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;

import io.tanks.client.cfg.ClientConfig;
import io.tanks.client.cfg.GameStyle;
import io.tanks.client.core.GameEngine;
import io.tanks.client.state.MatchInitState;
import io.tanks.client.state.menu.MainMenuState;
import io.tanks.client.view.GuiComponent;
import io.tanks.client.view.components.Button;
import io.tanks.client.view.components.Label;

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
  ClientConfig cfg;

  private io.tanks.client.view.components.Button btnJoinGame;
  private io.tanks.client.view.components.Button btnCancel;
  private io.tanks.client.view.components.Label lblServerStatus;
  private io.tanks.client.view.components.Label lblServerStatusValue;

  private boolean serverOnline;

  @PostConstruct
  public void initialize() {
    initComponents();
    initActions();
  }

  private void initComponents() {
    width = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_HEIGHT);
    io.tanks.client.view.components.Label label = new io.tanks.client.view.components.Label("Join Local Area Network Game", GameStyle.FONT_BIG_MESSAGE);
    label.setHorizontalAlignment(Label.HorizontalAlignment.CENTER);
    add(label, width / 2, 20);
    lblServerStatus = new io.tanks.client.view.components.Label("Server: ", GameStyle.FONT_MENU_BUTTON);
    add(lblServerStatus, 20, 80);
    lblServerStatusValue = new io.tanks.client.view.components.Label("Offline", GameStyle.FONT_MENU_BUTTON);
    lblServerStatusValue.setForegroundColor(GameStyle.RED);
    add(lblServerStatusValue, 100, 80);
    btnJoinGame = new io.tanks.client.view.components.Button("ENTER - Join LAN Game", GameStyle.FONT_MENU_BUTTON);
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
