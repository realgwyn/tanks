package org.game.tanks.database.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.game.tanks.database.domain.MalformedPacketHistory;
import org.game.tanks.database.domain.UnauthorizedActionHistory;
import org.game.tanks.database.domain.User;
import org.game.tanks.network.model.TCPMessage;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

//TODO: find out a way that save to DB will be invoked in new thread, 
//so it will not slow down main loop logic
@Component
public class DatabaseService {

  private Gson gson;

  @PostConstruct
  public void init() {
    gson = new Gson();
  }

  public void saveMalformedPacket(MalformedPacketHistory) {
    String json = gson.toJson(message);
    MalformedPacketHistory entity = new MalformedPacketHistory()
        .setSerializedObject(json)
        .setTime(new Date())
        .setIpAddress(ipAddress);
    //TODO: PERSIST
    throw new UnsupportedOperationException("Not implemented yet");
  }
  
  public void saveUnauthorizedAction(String ipAddress, Object commandObject) {
    String json = gson.toJson(commandObject);
    UnauthorizedActionHistory entity = new UnauthorizedActionHistory()
        .setIpAddress(ipAddress)
        .setSerializedObject(json)
        .setTime(new Date());
    //TODO: PERSIST
    throw new UnsupportedOperationException("Not implemented yet");
  }
  
  public User getUserByUsername(String username) {
    throw new UnsupportedOperationException("Not implemented yet");
  }


}
