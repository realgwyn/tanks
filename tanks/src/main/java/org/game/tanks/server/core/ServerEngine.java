package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.core.Loop;
import org.game.tanks.network.NetworkException;
import org.game.tanks.network.NetworkServer;
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
  ServerConfig config;

  private NetworkServer server;

  @Override
  public void run() {
    super.run(100);
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
      server.start(config.getTcpPort(), config.getUdpPort());
    } catch (NetworkException e) {
      serverWindow.setStatus("Network error");
      e.printStackTrace();
      return;
    }
    currentState = loadingMapState;
    logger.debug("Server running...");
  }

  @Override
  public void shutdown() {
    logger.debug("Shutting down server threads...");
    currentState = offlineState;
    server.stop();
    logger.debug("Server stopped...");
  }

  @Override
  public void update() {
    currentState.update();
  }

  @Override
  public void render() {

  }

}
