package org.game.tanks.server.core;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.Loop;
import org.game.tanks.network.NetworkException;
import org.game.tanks.network.NetworkServer;
import org.game.tanks.server.core.process.GameEventHandler;
import org.game.tanks.server.state.InitializingMatchServerState;
import org.game.tanks.server.state.OfflineServerState;
import org.game.tanks.server.state.ServerState;
import org.game.tanks.server.view.ServerWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerEngine extends Loop {

  private final static Logger logger = Logger.getLogger(ServerEngine.class);
  private ServerState currentState;

  @Autowired
  OfflineServerState offlineState;
  @Autowired
  InitializingMatchServerState loadingMapState;
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
    logger.debug("Initializing ServerContext...");
    serverContext.init();
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
    logger.debug("Changing Server State from " + currentState.getClass().getSimpleName()
        + " to: " + state.getClass().getSimpleName());

    if (currentState.getType().equals(state.getType())) {
      throw new UnsupportedOperationException("Flow error - its prohibited for state to change to itself");
    }

    currentState.onStateEnd();
    currentState = state;
    currentState.onStateBegin();
  }

  public ServerState getState() {
    return currentState;
  }

}
