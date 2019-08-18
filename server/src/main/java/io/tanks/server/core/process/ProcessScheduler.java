package io.tanks.server.core.process;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.server.config.ServerConfig;

@Component
public class ProcessScheduler {

  private boolean schedulerEnabled;

  @Autowired
  ProcessSchedulerContext scheduleContext;
  @Autowired
  ServerConfig config;
  @Autowired
  PlayerMovementProcessor playerMovementsHandler;
  @Autowired
  GameEventProcessor gameEventHandler;
  @Autowired
  GameCommandProcessor gameCommandHandler;
  @Autowired
  ChatMessageProcessor communicationHandler;
  @Autowired
  PlayerConnectionProcessor playerConnectionHandler;
  @Autowired
  AdminCommandProcessor adminCommandHandler;

  // Processes that must be executed in every loop
  private List<ScheduledProcess> highPriorityProcesses;
  // Processes that don't need to be executed in every loop
  private List<ScheduledProcess> lowPriorityProcesses;
  // Allowed max time of execution for runProcesses() method
  //TODO: this should be adjustable by the engine
  private long maxTimeframeValue = Long.MAX_VALUE;

  @PostConstruct
  private void init() {
    highPriorityProcesses = new ArrayList<>();
    highPriorityProcesses.add(playerConnectionHandler);
    highPriorityProcesses.add(playerMovementsHandler);
    highPriorityProcesses.add(gameEventHandler);
    lowPriorityProcesses = new ArrayList<>();
    lowPriorityProcesses.add(adminCommandHandler);
    lowPriorityProcesses.add(gameCommandHandler);
    lowPriorityProcesses.add(communicationHandler);
    schedulerEnabled = config.isProcessScheduler();
  }

  public void reinitialize() {
    scheduleContext.reinitialize();
  }

  public void runProcesses() {
    if (schedulerEnabled) {
      long startTime = System.currentTimeMillis();
      for (ScheduledProcess process : highPriorityProcesses) {
        process.execute();
      }

      if (allowedProcessTimeExceeded(startTime)) {
        return;
      }

      for (ScheduledProcess process : lowPriorityProcesses) {
        process.execute();
        if (allowedProcessTimeExceeded(startTime)) {
          return;
        }
      }
    } else {
      adminCommandHandler.execute();
      playerConnectionHandler.execute();
      playerMovementsHandler.execute();
      gameEventHandler.execute();
      gameCommandHandler.execute();
      communicationHandler.execute();
    }
  }
  
  private boolean allowedProcessTimeExceeded(long startTime){
    long afterProcessTime = System.currentTimeMillis();
    return afterProcessTime - startTime > maxTimeframeValue;
  }

  public void setSchedulerEnabled(boolean schedulerEnabled) {
    this.schedulerEnabled = schedulerEnabled;
  }

  public boolean isSchedulerEnabled() {
    return schedulerEnabled;
  }

}
