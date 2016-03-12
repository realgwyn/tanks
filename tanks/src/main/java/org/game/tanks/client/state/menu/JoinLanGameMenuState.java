package org.game.tanks.client.state.menu;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.ClientNetworkAdapter;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.state.ClientState;
import org.game.tanks.client.view.menu.JoinLanGameMenuWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinLanGameMenuState extends ClientState {

  static final Logger logger = Logger.getLogger(MainMenuState.class);

  @Autowired
  Config cfg;
  @Autowired
  GameDisplay display;
  @Autowired
  GameEngine engine;
  @Autowired
  GuiManager guiManager;
  @Autowired
  JoinLanGameMenuWindow joinLanGameMenuWindow;
  @Autowired
  ClientNetworkAdapter networkAdapter;

  private int serverTcpPort;
  private int serverUdpPort;

  private int animationCounter;

  public JoinLanGameMenuState() {
    super(ClientStateType.JOIN_LAN_GAME_MENU);
  }

  @Override
  public void onStateBegin() {
    display.requestFocus();
    guiManager.showAndFocusComponent(joinLanGameMenuWindow);
    serverTcpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT);
    serverUdpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT);
  }

  @Override
  public void update() {
    animationCounter++;

    if (everyTimePeriod(800)) {
      InetAddress hostAddress = networkAdapter.discoverLanHost(serverUdpPort, 500);
      if (hostAddress != null) {
        joinLanGameMenuWindow.setServerOnline(true);
      } else {
        joinLanGameMenuWindow.setServerOnline(false);
      }
    }

  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = animationCounter + i / 2;
    }
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }
}
