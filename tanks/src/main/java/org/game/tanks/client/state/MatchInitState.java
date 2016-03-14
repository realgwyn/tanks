package org.game.tanks.client.state;

import java.awt.Color;
import java.awt.Graphics;

import javax.annotation.PostConstruct;

import org.game.tanks.client.core.ClientContext;
import org.game.tanks.client.core.ClientEventBus;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.service.ServerConnectionService;
import org.game.tanks.client.state.menu.MainMenuState;
import org.game.tanks.client.view.MatchInitWindow;
import org.game.tanks.client.view.components.MessageWindow;
import org.game.tanks.network.NetworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
