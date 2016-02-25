package org.game.tanks.server.core.process;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessScheduler {

  private boolean schedulerEnabled;

  @Autowired
  Config config;
  @Autowired
  PlayerMovementsHandler playerMovementsHandler;
  @Autowired
  GameEventHandler gameEventHandler;
  @Autowired
  GameCommandHandler gameCommandHandler;
  @Autowired
  CommunicationMessageHandler communicationHandler;
  @Autowired
  PlayerConnectionHandler playerConnectionHandler;

  // Processes that must be executed in every loop
  private List<ScheduledProcess> mandatoryProcessSchedule;
  // Processes that don't need to be executed in every loop
  private List<ScheduledProcess> processSchedule;
  // Allowed max time of execution for runProcesses() method
  private long maxTimeframeValue = Long.MAX_VALUE;

  @PostConstruct
  private void initProcessSchedules() {
    mandatoryProcessSchedule = new ArrayList<>();
    mandatoryProcessSchedule.add(playerConnectionHandler);
    mandatoryProcessSchedule.add(playerMovementsHandler);
    mandatoryProcessSchedule.add(gameEventHandler);
    processSchedule = new ArrayList<>();
    processSchedule.add(gameCommandHandler);
    processSchedule.add(communicationHandler);
    schedulerEnabled = config.getPropertyBoolean(Config.SERVER_PROCESS_SCHEDULER_ENABLED);
  }

  public void runProcesses() {
    if (schedulerEnabled) {
      long startTime = System.currentTimeMillis();
      for (ScheduledProcess process : mandatoryProcessSchedule) {
        process.runProcess();
      }

      long afterProcessTime = System.currentTimeMillis();
      if (afterProcessTime - startTime > maxTimeframeValue) {
        return;
      }

      for (ScheduledProcess process : processSchedule) {
        process.runProcess();
        afterProcessTime = System.currentTimeMillis();
        if (afterProcessTime - startTime > maxTimeframeValue) {
          return;
        }
      }
    } else {
      playerConnectionHandler.runProcess();
      playerMovementsHandler.runProcess();
      gameEventHandler.runProcess();
      gameCommandHandler.runProcess();
      communicationHandler.runProcess();
    }
  }

  public void setSchedulerEnabled(boolean schedulerEnabled) {
    this.schedulerEnabled = schedulerEnabled;
  }

  public boolean isSchedulerEnabled() {
    return schedulerEnabled;
  }

}
