package io.game.tanks.client.core;


import java.awt.*;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.cfg.ClientConfig;
import io.game.tanks.client.state.ClientState;
import io.game.tanks.client.state.menu.LoadingGameState;
import io.game.tanks.network.NetworkClient;
import io.tanks.common.core.Loop;

/**
 * 
 * @author rafal.kojta
 */
@Component
public class GameEngine extends Loop {

  private final static Logger logger = Logger.getLogger(GameEngine.class);
  private ClientState currentState;

  @Autowired
  LoadingGameState loadingState;
  @Autowired
  GameDisplay display;
  @Autowired
  ClientConfig config;
  @Autowired
  PlayerInput input;
  @Autowired
  GuiManager guiManager;
  @Autowired
  ClientNetworkAdapter networkAdapter;

  private NetworkClient networkClient;

  @PostConstruct
  public void init() {
    currentState = loadingState;
    input.setInputListener(guiManager);
  }

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    super.run(config.getPropertyInt(ClientConfig.GAME_UPDATE_RATE));
  }

  @Override
  public synchronized void stop() {
    runFlag = false;
  }

  @Override
  public void startup() {
    logger.debug("Starting up Game Engine...");
    initGraphics();
    display.addMouseListener(input);
    display.addKeyListener(input);
    display.addMouseMotionListener(input);
    networkClient = new NetworkClient();
    networkAdapter.setClient(networkClient);
  }

  private void initGraphics() {
    logger.debug("Initializing Graphics...");
    JFrame window = display.getWindow();
    if (config.getPropertyBoolean("game.fullscreen")) {
      GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
      window.setUndecorated(true);
      window.setExtendedState(JFrame.MAXIMIZED_BOTH);
      window.pack();
      device.setFullScreenWindow(window);
    } else {
      window.setResizable(false);
      window.pack();
      window.setLocationRelativeTo(null);
    }
    window.setVisible(true);
  }

  @Override
  public void shutdown() {
    logger.debug("Shutting down game threads...");

    // TODO: send disconnect message if connected to server
    System.exit(0);
  }

  @Override
  public void update() {
    currentState.update();
    guiManager.update();
  }

  @Override
  public void render() {
    display.clearScreen();
    currentState.draw();
    guiManager.draw();
    display.render();
  }

  public void setState(ClientState state) {
    logger.debug("Changing Game State fro1.5m " + currentState.getClass().getSimpleName()
        + " to: " + state.getClass().getSimpleName());

    if (currentState.getType().equals(state.getType())) {
      throw new UnsupportedOperationException("Flow error - its prohibited for state to change to itself");
    }

    currentState.stateEnd();
    currentState = state;
    currentState.stateBegin();
  }

  public ClientState getCurrentState() {
    return currentState;
  }

}
