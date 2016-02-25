package org.game.tanks.server.core;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.core.Loop;
import org.game.tanks.network.NetworkException;
import org.game.tanks.network.NetworkServer;
import org.game.tanks.server.core.process.GameEventHandler;
import org.game.tanks.server.state.LoadingMapState;
import org.game.tanks.server.state.OfflineState;
import org.game.tanks.server.state.ServerState;
import org.game.tanks.server.view.ServerWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerEngine extends Loop {

  private final static Logger logger = Logger.getLogger(ServerEngine.class);
  private ServerState currentState;

  @Autowired
  OfflineState offlineState;
  @Autowired
  LoadingMapState loadingMapState;
  @Autowired
  ServerWindow serverWindow;
  @Autowired
  ServerContext serverContext;
  @Autowired
  ServerNetworkAdapter networkAdapter;
  @Autowired
  GameEventHandler gameEventHandler;
  @Autowired
  MessageSendingThread messageSendingThread;
  @Autowired
  PlayerConnectionThread playerConnectionThread;
  @Autowired
  Config config;

  private NetworkServer server;

  @PostConstruct
  public void init() {
    currentState = offlineState;
  }

  @Override
  public void run() {
    super.run(config.getPropertyInt(Config.SERVER_UPDATE_RATE));
  }

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public synchronized void stop() {
    super.stop();
  }

  @Override
  public void startup() {
    logger.debug("Starting server threads...");
    server = new NetworkServer();
    try {
      server.start(serverContext.getTcpPort(), serverContext.getUdpPort());
    } catch (NetworkException e) {
      serverWindow.setStatus("Network error");
      e.printStackTrace();
      return;
    }
    currentState = loadingMapState;
    networkAdapter.setServer(server);
    messageSendingThread.start();
    playerConnectionThread.start();

    logger.debug("Server running...");
  }

  @Override
  public void shutdown() {
    logger.debug("Shutting down server threads...");
    currentState = offlineState;
    server.stop();
    messageSendingThread.finish();
    playerConnectionThread.finish();
    logger.debug("Server stopped...");
  }

  @Override
  public void update() {
    currentState.update();
  }

  @Override
  public void render() {

  }

  public void setState(ServerState state) {
    logger.debug("Changing Server State to: " + state.getClass().getSimpleName());
    currentState.onStateEnd();

    currentState = state;
    currentState.onStateBegin();
  }

  public ServerState getState() {
    return currentState;
  }

}
