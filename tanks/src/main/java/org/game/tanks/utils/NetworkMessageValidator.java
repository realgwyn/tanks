package org.game.tanks.utils;

import java.util.Date;

import org.game.tanks.database.domain.MalformedPacketHistory;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.server.core.TaskManager;
import org.game.tanks.server.core.task.DatabaseTask.DatabaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class NetworkMessageValidator {

  @Autowired
  TaskManager taskManager;

  public boolean isValid(Connection conn, GameEvent gameEvent) {
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      MalformedPacketHistory entity = new MalformedPacketHistory()
          .setObject(gameEvent)
          .setTime(new Date())
          .setIpAddress(conn.getRemoteAddressTCP().getHostName());

      taskManager.createDbTask(entity, DatabaseAction.CREATE);
    }
    return valid;
  }

  public boolean isValid(Connection conn, Command command) {
    // TODO
    return true;
  }

  public boolean isValid(Connection conn, CommunicationMessage comMsg) {
    // TODO
    return true;
  }

  public boolean isValid(Connection conn, AdminCommand admCmd) {
    // TODO
    return true;
  }

}
