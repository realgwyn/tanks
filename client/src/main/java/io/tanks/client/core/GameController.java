package io.tanks.client.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.cfg.ClientConfig;
import io.tanks.client.service.ServerConnectionService;
import io.tanks.client.state.MatchInitState;
import io.tanks.client.state.menu.FindGameOnlineMenuState;
import io.tanks.client.view.components.MessageWindow;

@Component
public class GameController {

  private final static Logger logger = Logger.getLogger(GameController.class);

  @Autowired
  ClientConfig cfg;
  @Autowired
  ClientNetworkAdapter networkAdapter;
  @Autowired
  GameEngine engine;
  @Autowired
  MatchInitState matchInitState;
  @Autowired
  FindGameOnlineMenuState findGameMenuState;
  @Autowired
  MessageWindow messageWindow;
  @Autowired
  ServerConnectionService serverConnectionService;

  public void connectToServer() {
    // // Invoke disconnect if was connected to other server
    // disconnectFromServer();
    // Connection connection = networkAdapter.connectToServer(ctx.getServerAddress(), ctx.getServerTcpPort(),
    // ctx.getServerUdpPort());
    // if (connection != null) {
    // engine.setState(matchInitState);
    // messageWindow.showInfoMessage("Connecting to server...");
    // } else {
    // engine.setState(findGameMenuState);
    // messageWindow.showErrorMessage("Error connecting to server");
    // }
  }

  public void leaveTheMatch() {
    disconnectFromServer();
    engine.setState(findGameMenuState);
  }

  private void disconnectFromServer() {
    logger.debug("Disconnecting from server...");
    // TODO: send Disconnect msg to server

    logger.debug("Stopping all game threads...");
    // TODO: stop network listening thread
    // TODO: stop network sending thread

    logger.debug("Clearing GameContext...");

    logger.debug("Clearing sheduled tasks");
    // TODO

  }

  public void exitGame() {
    disconnectFromServer();
    shutdown();
  }

  public void shutdown() {
    engine.stop();
  }

}
