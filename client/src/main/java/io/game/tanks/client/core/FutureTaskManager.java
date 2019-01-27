package io.game.tanks.client.core;

import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FutureTaskManager {

  public enum SupportedAction {
    CONNECT_TO_SERVER, LEAVE_MATCH, EXIT_GAME
  }

  @Autowired
  ClientNetworkAdapter networkAdapter;

  ExecutorService executor;

  @PostConstruct
  public void init() {
    executor = Executors.newFixedThreadPool(10);
  }

  @Autowired
  GameController controller;

  public Future<TaskResult> runDiscoverLanHostTask(int serverUdpPort, int timeout) {
    Callable<TaskResult> hostDiscoverTask = () -> {
      TaskResult result = new TaskResult();
      InetAddress addr = networkAdapter.discoverLanHost(serverUdpPort, timeout);
      result.setWrappedObject(addr);
      return result;
    };

    return executor.submit(hostDiscoverTask);
  }

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
