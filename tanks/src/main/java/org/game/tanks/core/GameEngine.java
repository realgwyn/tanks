package org.game.tanks.core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.game.tanks.cfg.Config;
import org.game.tanks.state.LoadingState;
import org.game.tanks.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author rafal.kojta
 */
@Component
public class GameEngine extends Loop {

  final static Logger logger = Logger.getLogger(GameEngine.class);
  State currentState;

  @Autowired
  LoadingState loadingState;
  @Autowired
  GameDisplay display;
  @Autowired
  Config config;
  @Autowired
  PlayerInput input;

  @PostConstruct
  public void init() {
    currentState = loadingState;
    input.setKeyTypedListener(currentState);
  }

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    super.run(config.getPropertyInt("game.updaterate"));
  }

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
  }

  @Override
  public void render() {
    currentState.draw();
    display.render();
  }

  public void setState(State state) {
    logger.debug("Changing Game State to: " + state.getClass().getSimpleName());
    currentState.onStateEnd();
    currentState = state;
    input.setKeyTypedListener(currentState);
    currentState.onStateBegin();
  }

}
