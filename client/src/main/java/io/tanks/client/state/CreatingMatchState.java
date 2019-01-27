package io.tanks.client.state;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.cfg.ClientConfig;
import io.tanks.client.core.ClientContext;
import io.tanks.client.core.GameDisplay;
import io.tanks.client.core.GameEngine;
import io.tanks.client.core.GuiManager;
import io.tanks.common.network.ConnectionAddress;
import io.tanks.common.network.state.ClientStateType;

@Component
public class CreatingMatchState extends ClientState {

  @Autowired
  ClientConfig cfg;
  @Autowired
  ClientContext clientContext;
  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  WaitingForPlayersState waitingForPlayersState;
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
    defaultServerTcpPort = cfg.getPropertyInt(ClientConfig.SERVER_DEFAULT_TCP_PORT);
    defaultServerUdpPort = cfg.getPropertyInt(ClientConfig.SERVER_DEFAULT_UDP_PORT);
  }

  @Override
  public void onStateBegin() {
    clientContext.setHostingGame(true);

    //TODO: server.startServer("Tanks LAN Game", defaultServerTcpPort, defaultServerUdpPort);
    ConnectionAddress serverAddress = new ConnectionAddress()
        .setAddress("localhost")
        .setTcpPort(defaultServerTcpPort)
        .setUdpPort(defaultServerUdpPort);
    
    clientContext.setServerAddress(serverAddress);
    throw new UnsupportedOperationException("Implement Admin communication with server");
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
