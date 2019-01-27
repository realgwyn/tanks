package io.tanks.client.state.menu;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.cfg.ClientConfig;
import io.tanks.client.core.ClientContext;
import io.tanks.client.core.ClientNetworkAdapter;
import io.tanks.client.core.FutureTaskManager;
import io.tanks.client.core.GameDisplay;
import io.tanks.client.core.GameEngine;
import io.tanks.client.core.GuiManager;
import io.tanks.client.core.TaskResult;
import io.tanks.client.state.ClientState;
import io.tanks.client.view.menu.JoinLanGameMenuWindow;
import io.tanks.common.network.ConnectionAddress;
import io.tanks.common.network.state.ClientStateType;

@Component
public class JoinLanGameMenuState extends ClientState {

  static final Logger logger = Logger.getLogger(MainMenuState.class);

  @Autowired
  ClientConfig cfg;
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
    serverTcpPort = cfg.getPropertyInt(ClientConfig.SERVER_DEFAULT_TCP_PORT);
    serverUdpPort = cfg.getPropertyInt(ClientConfig.SERVER_DEFAULT_UDP_PORT);
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
