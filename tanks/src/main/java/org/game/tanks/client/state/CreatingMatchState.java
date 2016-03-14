package org.game.tanks.client.state;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.ClientContext;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GameEngine;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.network.ConnectionAddress;
import org.game.tanks.server.core.ServerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatingMatchState extends ClientState {

  @Autowired
  Config cfg;
  @Autowired
  ClientContext clientContext;
  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  WaitingForPlayersState waitingForPlayersState;
  @Autowired
  ServerController serverController;
  @Autowired
  MatchInitState matchInitState;
  @Autowired
  GameEngine engine;

  public CreatingMatchState() {
    super(ClientStateType.CREATING_MATCH);
  }

  private int defaultServerTcpPort;
  private int defaultServerUdpPort;

  @PostConstruct
  public void init() {
    defaultServerTcpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT);
    defaultServerUdpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT);
  }

  @Override
  public void onStateBegin() {
    clientContext.setHostingGame(true);
    serverController.startServer("Tanks LAN Game", defaultServerTcpPort, defaultServerUdpPort);
    ConnectionAddress serverAddress = new ConnectionAddress()
        .setAddress("localhost")
        .setTcpPort(defaultServerTcpPort)
        .setUdpPort(defaultServerUdpPort);
    clientContext.setServerAddress(serverAddress);
  }

  @Override
  public void update() {
    engine.setState(matchInitState);
  }

  @Override
  public void draw() {

  }

  @Override
  public void onStateEnd() {

  }

}
