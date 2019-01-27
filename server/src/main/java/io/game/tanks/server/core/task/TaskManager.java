package io.game.tanks.server.core.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.model.AdminCommand;
import io.game.tanks.network.model.message.ServerMessage;
import io.game.tanks.server.core.MessageSendingThread;
import io.game.tanks.server.core.ServerContext;
import io.game.tanks.server.core.ServerEventBus;
import io.game.tanks.server.core.ServerNetworkAdapter;
import io.game.tanks.server.core.task.DatabaseTask.DatabaseAction;
import io.game.tanks.server.database.DatabaseService;
import io.game.tanks.server.database.domain.MalformedPacketHistory;
import io.game.tanks.server.service.AuthenticationService;

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
  ServerEventBus bus;
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

  public void createTask(AdminCommand command) {
    submitTask(command);
  }

  public void createDbTask(Object entity, DatabaseAction action) {
    submitTask(new DatabaseTask(entity, action));
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
      bus.getIncomingAdminCommands().add(adminCommand);
    } else {
      ServerMessage msg = new ServerMessage();
      msg.setConnectionIdTo(adminCommand.getConnectionIdFrom());
      msg.setType(ServerMessage.ServerMessageType.FORBIDDEN);
      msg.setText("You are not authenticated to execute this command");
      bus.getOutgoingCommunicationMessages().add(msg);
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

}
