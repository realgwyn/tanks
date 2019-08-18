package io.tanks.server.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

import io.tanks.common.network.model.AdminCommand;
import io.tanks.common.network.model.Command;
import io.tanks.common.network.model.CommunicationMessage;
import io.tanks.common.network.model.GameEvent;
import io.tanks.server.config.ServerConfig;
import io.tanks.server.core.task.DatabaseTask;
import io.tanks.server.core.task.TaskManager;
import io.tanks.server.database.domain.MalformedPacketHistory;

@Component
public class NetworkMessageValidator {

  @Autowired
  TaskManager taskManager;
  
  @Autowired
  ServerConfig config;
  
  boolean packetValidationEnabled;

  @PostConstruct
  public void init() {
    packetValidationEnabled = config.isPacketValidation();
  }

  public boolean isValid(Connection conn, GameEvent gameEvent) {
    if(!packetValidationEnabled){
      return true;
    }
    
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      saveMalformedPacket(conn, gameEvent);
    }
    return valid;
  }

  public boolean isValid(Connection conn, Command command) {
    if(!packetValidationEnabled){
      return true;
    }
    
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      saveMalformedPacket(conn, command);
    }
    return valid;
  }

  public boolean isValid(Connection conn, CommunicationMessage comMsg) {
    if(!packetValidationEnabled){
      return true;
    }
    
    boolean valid = true;

    // TODO: some validation

    if (!valid) {
      saveMalformedPacket(conn, comMsg);
    }
    return valid;
  }

  public boolean isValid(Connection conn, AdminCommand admCmd) {
    if(!packetValidationEnabled){
      return true;
    }
    
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
    taskManager.createDbTask(entity, DatabaseTask.DatabaseAction.CREATE);
  }

}
