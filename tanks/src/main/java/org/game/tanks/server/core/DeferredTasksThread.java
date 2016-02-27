package org.game.tanks.server.core;

import org.apache.log4j.Logger;
import org.game.tanks.database.domain.MalformedPacketHistory;
import org.game.tanks.database.service.DatabaseService;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.message.ServerMessage;
import org.game.tanks.network.model.message.ServerMessage.ServerMessageType;
import org.game.tanks.security.AuthenticationService;
import org.game.tanks.server.core.task.DatabaseTask;
import org.game.tanks.server.core.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeferredTasksThread implements Runnable {

  private final static Logger logger = Logger.getLogger(MessageSendingThread.class);
  private boolean running = true;

  @Autowired
  ServerContext ctx;
  @Autowired
  AuthenticationService authService;
  @Autowired
  DatabaseService dbService;
  @Autowired
  ServerNetworkAdapter serverNetworkAdapter;

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    logger.debug("Running...");
    running = true;
    while (running) {
      processSlowTasks();
      try {
        Thread.sleep(50);// Sleep a little bit
      } catch (InterruptedException e) {
      }
    }
  }


  public void finish() {
    logger.debug("Stopping...");
    running = false;
  }

  private void processSlowTasks() {
    while(!ctx.getPendingTasks().isEmpty()){
      Task task = ctx.getPendingTasks().poll();
      Object cmd = task.getCommand();
      if(cmd instanceof AdminCommand){
        processAdminCommand((AdminCommand) cmd);
      } else if (cmd instanceof DatabaseTask) {
        processDbTask((DatabaseTask) cmd);
      }
    }
  }

  private void processAdminCommand(AdminCommand adminCommand) {
    if (authService.authenticateCommand(adminCommand)) {
      ctx.getIncomingAdminCommands().add(adminCommand);
    } else {
      ServerMessage msg = new ServerMessage();
      msg.setConnectionIdTo(adminCommand.getConnectionIdFrom());
      msg.setType(ServerMessageType.FORBIDDEN);
      msg.setText("You are not authenticated to execute this command");
      ctx.getOutgoingCommunicationMessages().add(msg);
      String ipAddress = serverNetworkAdapter.getIpAddressByConectionId(msg.getConnectionIdFrom());
      dbService.saveUnauthorizedAction(ipAddress, msg);
    }
  }

  private void processDbTask(DatabaseTask dbTask) {
    switch (dbTask.getAction()) {
    case CREATE:
      createDbObject(dbTask.getDataObject());
      break;
    case UPDATE:
      updateDbObject(dbTask.getDataObject());
      break;
    case DELETE:
      deleteDbObject(dbTask.getDataObject());
      break;
    }
  }

  private void createDbObject(Object dataObject) {
    if (dataObject instanceof MalformedPacketHistory) {
      dbService.saveMalformedPacket((MalformedPacketHistory) dataObject);
    }else{
      throw new UnsupportedOperationException("Not supported database action object " + dataObject.getClass().getSimpleName());
    }
  }

  private void updateDbObject(Object dataObject) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  private void deleteDbObject(Object dataObject) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

}
