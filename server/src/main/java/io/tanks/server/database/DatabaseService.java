package io.tanks.server.database;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.tanks.server.database.domain.MalformedPacketHistory;
import io.tanks.server.database.domain.UnauthorizedActionHistory;
import io.tanks.server.database.repository.MalformedPacketHistoryRepo;
import io.tanks.server.database.repository.PlayerRepo;
import io.tanks.server.database.repository.UnauthorizedActionHistoryRepo;

@Service
public class DatabaseService {

  @Autowired
  PlayerRepo playerRepo;
  
  @Autowired
  UnauthorizedActionHistoryRepo unauthorizedActionHistoryRepo;
  
  @Autowired
  MalformedPacketHistoryRepo malformedPacketHistoryRepo;
  
  private Gson gson;

  @PostConstruct
  public void init() {
    gson = new Gson();
  }

  public void saveMalformedPacket(MalformedPacketHistory message) {
    String json = gson.toJson(message.getObject());
    message.setSerializedObject(json);
    malformedPacketHistoryRepo.save(message);
  }

  public void saveUnauthorizedAction(String ipAddress, Object commandObject) {
    String json = gson.toJson(commandObject);
    UnauthorizedActionHistory entity = new UnauthorizedActionHistory()
        .setIpAddress(ipAddress)
        .setSerializedObject(json)
        .setTime(new Date());
    unauthorizedActionHistoryRepo.save(entity);
  }


}
