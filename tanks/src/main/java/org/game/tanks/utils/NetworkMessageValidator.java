package org.game.tanks.utils;

import java.util.Date;

import org.game.tanks.database.domain.MalformedPacketHistory;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.server.core.task.TaskManager;
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
      saveMalformedPacket(conn, gameEvent);
    }
    return valid;
  }

  public boolean isValid(Connection conn, Command command) {
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      saveMalformedPacket(conn, command);
    }
    return valid;
  }

  public boolean isValid(Connection conn, CommunicationMessage comMsg) {
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      saveMalformedPacket(conn, comMsg);
    }
    return valid;
  }

  public boolean isValid(Connection conn, AdminCommand admCmd) {
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      saveMalformedPacket(conn, admCmd);
    }
    return valid;
  }

  private void saveMalformedPacket(Connection conn, Object message) {
    MalformedPacketHistory entity = new MalformedPacketHistory()
        .setObject(message)
        .setTime(new Date())
        .setIpAddress(conn.getRemoteAddressTCP().getHostName());
    taskManager.createDbTask(entity, DatabaseAction.CREATE);
  }

}
