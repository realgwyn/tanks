package io.tanks.server.core.process;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.server.cfg.ServerConfig;

@Component
public class ProcessScheduler {

  private boolean schedulerEnabled;

  @Autowired
  SchedulerContext scheduleContext;
  @Autowired
  ServerConfig config;
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
  @Autowired
  AdminCommandHandler adminCommandHandler;

  // Processes that must be executed in every loop
  private List<ScheduledProcess> mandatoryProcessSchedule;
  // Processes that don't need to be executed in every loop
  private List<ScheduledProcess> processSchedule;
  // Allowed max time of execution for runProcesses() method
  private long maxTimeframeValue = Long.MAX_VALUE;

  @PostConstruct
  private void initProcessSchedules() {
    mandatoryProcessSchedule = new ArrayList<>();
    mandatoryProcessSchedule.add(adminCommandHandler);
    mandatoryProcessSchedule.add(playerConnectionHandler);
    mandatoryProcessSchedule.add(playerMovementsHandler);
    mandatoryProcessSchedule.add(gameEventHandler);
    processSchedule = new ArrayList<>();
    processSchedule.add(gameCommandHandler);
    processSchedule.add(communicationHandler);
    schedulerEnabled = config.getPropertyBoolean(ServerConfig.SERVER_ENABLE_PROCESS_SCHEDULER);
  }

  public void reinitialize() {
    scheduleContext.reinitialize();
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
      adminCommandHandler.runProcess();
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
