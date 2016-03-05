package org.game.tanks.client.core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.client.state.ClientState;
import org.game.tanks.client.state.LoadingGameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
  Config config;
  @Autowired
  PlayerInput input;
  @Autowired
  GuiManager guiManager;

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
    super.run(config.getPropertyInt(Config.GAME_UPDATE_RATE));
  }

  @Override
  public synchronized void stop() {
    runFlag = false;
  }

  @Override
  public void startup() {
    logger.debug("Starting up Engine...");
    initGraphics();
    display.addMouseListener(input);
    display.addKeyListener(input);
    display.addMouseMotionListener(input);
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
    logger.debug("Changing Game State from " + currentState.getClass().getSimpleName()
        + " to: " + state.getClass().getSimpleName());

    if (currentState.getType().equals(state.getType())) {
      throw new UnsupportedOperationException("Flow error - its prohibited for state to change to itself");
    }

    currentState.onStateEnd();
    currentState = state;
    currentState.onStateBegin();
  }

  public ClientState getCurrentState() {
    return currentState;
  }

}
