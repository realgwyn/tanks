package org.game.tanks.client.state;

import java.awt.Color;
import java.awt.Graphics;

import javax.annotation.PostConstruct;

import org.game.tanks.client.core.GameContext;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.service.ServerConnectionService;
import org.game.tanks.client.view.MatchInitWindow;
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
  GameContext ctx;

  public MatchInitState() {
    super(ClientStateType.MATCH_INIT);
  }

  @PostConstruct
  public void init() {
  }

  @Override
  public void onStateBegin() {
    guiManager.showComponent(matchInitWindow);
    // TODO handhake server

  }

  @Override
  public void update() {
    serverConnectionService.processMatchInitCommands();
    // if(){

    // }
  }

  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(display.WIDTH / 2 - 80, display.HEIGHT / 2 - 20, 160, 40);
    g.setColor(Color.WHITE);
    g.drawString("MatchInitState", display.WIDTH / 2 - 75, display.HEIGHT / 2 + 4);
  }

  @Override
  public void onStateEnd() {
    guiManager.hideAllComponents();
  }

}
