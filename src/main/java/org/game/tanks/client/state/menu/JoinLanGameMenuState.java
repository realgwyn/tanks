package org.game.tanks.client.state.menu;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.ClientContext;
import org.game.tanks.client.core.ClientNetworkAdapter;
import org.game.tanks.client.core.FutureTaskManager;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.core.TaskResult;
import org.game.tanks.client.state.ClientState;
import org.game.tanks.client.view.menu.JoinLanGameMenuWindow;
import org.game.tanks.network.ConnectionAddress;
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
  @Autowired
  FutureTaskManager taskManager;
  @Autowired
  ClientContext clientContext;

  private int serverTcpPort;
  private int serverUdpPort;
  private Future<TaskResult> discovderLanHostTaskFuture = null;

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
    runLanHostDiscoverTask();
  }

  private void runLanHostDiscoverTask() {

    if (everyTimePeriod(800)) {
      discovderLanHostTaskFuture = taskManager.runDiscoverLanHostTask(serverUdpPort, 500);
    }

    if (discovderLanHostTaskFuture != null && discovderLanHostTaskFuture.isDone()) {
      InetAddress hostAddress;
      try {
        hostAddress = ((InetAddress) discovderLanHostTaskFuture.get().getWrappedObject());
        if (hostAddress != null) {
          ConnectionAddress connectionAddress = new ConnectionAddress()
              .setAddress(hostAddress.getHostAddress())
              .setTcpPort(serverTcpPort)
              .setUdpPort(serverUdpPort);
          clientContext.setServerAddress(connectionAddress);
          joinLanGameMenuWindow.setServerOnline(true);
        } else {
          joinLanGameMenuWindow.setServerOnline(false);
        }
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void draw() {
    int[] pixels = display.getRasterPixels();
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = animationCounter + i / 3;
    }
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }
}
