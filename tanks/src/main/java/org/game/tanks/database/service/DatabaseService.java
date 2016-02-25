package org.game.tanks.database.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.game.tanks.database.domain.MalformedPacketHistory;
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

  public void saveMalformedPacket(String ipAddress, TCPMessage message) {
    String json = gson.toJson(message);
    MalformedPacketHistory entity = new MalformedPacketHistory()
        .setSerializedObject(json)
        .setTime(new Date().getTime())
        .setIpAddress(ipAddress);
    //TODO: persist
  }

}
