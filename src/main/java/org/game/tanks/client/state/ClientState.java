package org.game.tanks.client.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;
import org.game.tanks.client.view.Focusable;

public abstract class ClientState implements Focusable {

  static final Logger logger = Logger.getLogger(ClientState.class);

  public enum ClientStateType {
    LOADING_GAME, MAIN_MENU, OPTIONS_MENU, CREATE_LAN_GAME_MENU, JOIN_LAN_GAME_MENU, FIND_GAME_ONLINE_MENU, CREATING_MATCH, MATCH_INIT, WAITING_FOR_PLAYERS, ROUND_INIT, ROUND_START, ROUND, ROUND_END, LEAVING_MATCH, MATCH_END, EXIT_GAME
  }

  private ClientStateType type;
  private long startTime;
  private long periodStartTime;

  public ClientState(ClientStateType type) {
    this.type = type;
  }

  public abstract void update();

  public abstract void draw();

  public void stateBegin() {
    startTime = System.currentTimeMillis();
    onStateBegin();
  }

  public abstract void onStateBegin();

  /**
   * NOTICE: Do not use in game states - might slow down the game loop
   * 
   * @returns true - if time passed since state started is greater than timeDelta (milliseconds)
   */
  public boolean timePassed(long timeDeltaMillis) {
    long currentTime = System.currentTimeMillis();
    return currentTime - startTime > timeDeltaMillis;
  }

  /**
   * Use when want to invoke some operation every time on fixed period NOTICE: Do not use in game states - might slow
   * down the game loop
   * 
   * @param timeDeltaMillis
   * @return true - periodicaly every timeDelta period
   */
  public boolean everyTimePeriod(long timeDeltaMillis) {
    long currentTime = System.currentTimeMillis();
    if (currentTime - periodStartTime > timeDeltaMillis) {

      periodStartTime = System.currentTimeMillis();
      return true;
    }
    return false;
  }

  public void stateEnd() {

    onStateEnd();
  }

  public abstract void onStateEnd();

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  public ClientStateType getType() {
    return type;
  }

}
