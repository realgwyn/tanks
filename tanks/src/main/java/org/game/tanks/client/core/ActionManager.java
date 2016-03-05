package org.game.tanks.client.core;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionManager {

  public enum SupportedAction {
    CONNECT_TO_SERVER, LEAVE_MATCH, EXIT_GAME
  }

  ExecutorService executor;

  @PostConstruct
  public void init() {
    executor = Executors.newFixedThreadPool(1);
  }

  @Autowired
  GameController controller;

  public void action(SupportedAction supportedAction) {
    Callable<Void> task;
    switch (supportedAction) {
    case CONNECT_TO_SERVER:
      task = () -> {
        controller.connectToServer();
        return null;
      };
      break;
    case LEAVE_MATCH:
      task = () -> {
        controller.leaveTheMatch();
        return null;
      };
      break;
    case EXIT_GAME:
      task = () -> {
        controller.exitGame();
        return null;
      };
      break;
    default:
      throw new UnsupportedOperationException("Not supported action " + supportedAction.getClass().getSimpleName());
    }

    executor.submit(task);
  }

}
