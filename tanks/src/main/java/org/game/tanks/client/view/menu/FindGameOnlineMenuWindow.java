package org.game.tanks.client.view.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.cfg.GameStyle;
import org.game.tanks.client.core.ClientContext;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.state.MatchInitState;
import org.game.tanks.client.state.menu.MainMenuState;
import org.game.tanks.client.view.GuiComponent;
import org.game.tanks.client.view.components.Button;
import org.game.tanks.client.view.components.Label;
import org.game.tanks.client.view.components.Label.HorizontalAlignment;
import org.game.tanks.network.ConnectionAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class FindGameOnlineMenuWindow extends GuiComponent {

  @Autowired
  Config cfg;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  MatchInitState matchInitState;
  @Autowired
  ClientContext clientContext;

  private Button btnCancel;
  private Button btnJoinGame;

  private int serverTcpPort;
  private int serverUdpPort;
  private String hostAddress;

  @PostConstruct
  public void initialize() {
    initComponents();
    initActions();
    hostAddress = "localhost";
    serverTcpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT);
    serverUdpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT);
  }

  private void initComponents() {
    width = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(Config.GAME_RESOLUTION_HEIGHT);

    Label label = new Label("Find Game Online", GameStyle.FONT_BIG_MESSAGE);
    label.setHorizontalAlignment(HorizontalAlignment.CENTER);
    add(label, width / 2, 20);

    btnJoinGame = new Button("ENTER - Join Game", GameStyle.FONT_MENU_BUTTON);
    btnJoinGame.setBorderEnabled(false);
    add(btnJoinGame, 20, 60);

    btnCancel = new Button("ESC - Cancel", GameStyle.FONT_MENU_BUTTON);
    btnCancel.setBorderEnabled(false);
    add(btnCancel, 20, height - 40);

  }

  private void initActions() {
    btnJoinGame.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {

        ConnectionAddress connectionAddress = new ConnectionAddress()
            .setAddress(hostAddress)
            .setTcpPort(serverTcpPort)
            .setUdpPort(serverUdpPort);
        clientContext.setServerAddress(connectionAddress);

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
      engine.setState(matchInitState);
      break;
    case KeyEvent.VK_ESCAPE:
      engine.setState(mainMenuState);
      break;
    default:
      break;
    }
  }

}
