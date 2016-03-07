package org.game.tanks.server.core.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.game.tanks.database.domain.MalformedPacketHistory;
import org.game.tanks.database.service.DatabaseService;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.message.ServerMessage;
import org.game.tanks.network.model.message.ServerMessage.ServerMessageType;
import org.game.tanks.security.AuthenticationService;
import org.game.tanks.server.core.MessageSendingThread;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerNetworkAdapter;
import org.game.tanks.server.core.task.DatabaseTask.DatabaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Invokes task in separate thread
 * 
 * @author rafal.kojta
 *
 */
@Component
public class TaskManager {

  private final static Logger logger = Logger.getLogger(MessageSendingThread.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  AuthenticationService authService;
  @Autowired
  DatabaseService dbService;
  @Autowired
  ServerNetworkAdapter serverNetworkAdapter;

  ExecutorService executor;

  @PostConstruct
  public void init() {
    executor = Executors.newFixedThreadPool(1);
  }

  private void submitTask(Object command) {
    Callable<Void> task;
    if (command instanceof AdminCommand) {
      task = () -> {
        processAdminCommand((AdminCommand) command);
        return null;
      };
    } else if (command instanceof DatabaseTask) {
      task = () -> {
        processDbTask((DatabaseTask) command);
        return null;
      };
    } else {
      throw new UnsupportedOperationException("Unable to process task " + command.getClass().getSimpleName());
    }

    executor.submit(task);
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
      createDbObject(dbTask.getCommand());
      break;
    case UPDATE:
      updateDbObject(dbTask.getCommand());
      break;
    case DELETE:
      deleteDbObject(dbTask.getCommand());
      break;
    }
  }

  private void createDbObject(Object dataObject) {
    if (dataObject instanceof MalformedPacketHistory) {
      dbService.saveMalformedPacket((MalformedPacketHistory) dataObject);
    } else {
      throw new UnsupportedOperationException("Not supported database action object " + dataObject.getClass().getSimpleName());
    }
  }

  private void updateDbObject(Object dataObject) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  private void deleteDbObject(Object dataObject) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void createTask(AdminCommand command) {
    submitTask(command);
  }

  public void createDbTask(MalformedPacketHistory entity, DatabaseAction action) {
    submitTask(new DatabaseTask(entity, action));
  }

}
