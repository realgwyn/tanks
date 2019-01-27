package io.game.tanks.client.state;


import java.awt.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.client.core.ClientContext;
import io.game.tanks.client.core.ClientEventBus;
import io.game.tanks.client.core.GameDisplay;
import io.game.tanks.client.core.GameEngine;
import io.game.tanks.client.core.GuiManager;
import io.game.tanks.client.service.ServerConnectionService;
import io.game.tanks.client.state.menu.MainMenuState;
import io.game.tanks.client.view.MatchInitWindow;
import io.game.tanks.client.view.components.MessageWindow;
import io.game.tanks.network.NetworkException;
import io.game.tanks.network.state.ClientStateType;

/**
 * Loading map, players, initializing game context, syncing with the server
 */
@Component
public class MatchInitState extends ClientState {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  MatchInitWindow matchInitWindow;
  @Autowired
  ServerConnectionService serverConnectionService;
  @Autowired
  ClientContext ctx;
  @Autowired
  GameEngine engine;
  @Autowired
  WaitingForPlayersState waitingForPlayersState;
  @Autowired
  MessageWindow messageWindow;
  @Autowired
  MainMenuState mainMenuState;
  @Autowired
  ClientEventBus bus;

  public MatchInitState() {
    super(ClientStateType.MATCH_INIT);
  }

  @PostConstruct
  public void init() {
  }

  @Override
  public void onStateBegin() {
    bus.flushEvents();
    ctx.reinitialize();
    guiManager.showComponent(matchInitWindow);
    try {
      serverConnectionService.connectToServer(ctx.getServerAddress());
    } catch (NetworkException e) {
      messageWindow.showErrorMessage("Error Connecting To Server: " + e.getMessage());
      e.printStackTrace();
      engine.setState(mainMenuState);
    }
  }

  @Override
  public void update() {
    serverConnectionService.processMatchInitCommands();
    if (serverConnectionService.isConnectionReady()) {
      engine.setState(waitingForPlayersState);
    }
  }

  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20, 160, 40);
    g.setColor(Color.WHITE);
    g.drawString("INITIALIZING MATCH", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}