package org.game.tanks.utils;

import org.game.tanks.database.service.DatabaseService;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class NetworkMessageValidator {

  @Autowired
  DatabaseService dbService;

  public boolean isValid(Connection conn, GameEvent gameEvent) {
    //TODO:
    //    if (invalid) {
    //      dbService.saveMalformedPacket(conn.getRemoteAddressTCP().toString(), gameEvent);
    //      return false;
    //    } else {
      return true;
    //    }
  }

  public boolean isValid(Connection conn, Command command) {
    //TODO
    return true;
  }

  public boolean isValid(Connection conn, CommunicationMessage comMsg) {
    //TODO
    return true;
  }

  public boolean isValid(Connection conn, AdminCommand admCmd) {
    //TODO
    return true;
  }

}
