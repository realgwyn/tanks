package org.game.tanks.server.core;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.client.core.Loop;
import org.game.tanks.network.NetworkException;
import org.game.tanks.network.NetworkServer;
import org.game.tanks.server.core.process.GameEventHandler;
import org.game.tanks.server.core.state.MatchInitServerState;
import org.game.tanks.server.core.state.OfflineServerState;
import org.game.tanks.server.core.state.ServerState;
import org.game.tanks.server.service.SyncStateService;
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
  MatchInitServerState matchInitState;
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
  SyncStateService syncStateService;
  @Autowired
  Config config;

  private boolean ready;

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
    Thread thread = new Thread(this);
    thread.setName("ServerEngineThread");
    thread.start();
  }

  @Override
  public synchronized void stop() {
    super.stop();
  }

  @Override
  public void startup() {
    logger.debug("Starting server threads...");
    try {
      if (server != null) {// If server was already running - shut down
        shutdown();
      }
      server = new NetworkServer();
      server.start(serverContext.getTcpPort(), serverContext.getUdpPort());
      networkAdapter.setServer(server);
    } catch (NetworkException e) {
      serverWindow.setStatus("Network error");
      e.printStackTrace();
      return;
    }

    setState(matchInitState);
    messageSendingThread.start();
    playerConnectionThread.start();

    logger.debug("Server running.");
    ready = true;
  }

  @Override
  public void shutdown() {
    logger.debug("Shutting down server threads...");
    currentState = offlineState;
    server.stop();
    messageSendingThread.finish();
    playerConnectionThread.finish();
    logger.debug("Server stopped.");
    ready = false;
  }

  @Override
  public void update() {
    currentState.update();
  }

  @Override
  public void render() {
    // TODO: maybe in render function perform
  }

  public void setState(ServerState state) {
    logger.debug("Changing Server State from " + currentState.getClass().getSimpleName()
        + " to: " + state.getClass().getSimpleName());

    currentState.stateEnd();
    currentState = state;
    currentState.stateBegin();
  }

  public ServerState getState() {
    return currentState;
  }

  public boolean isReady() {
    return ready;
  }

}
