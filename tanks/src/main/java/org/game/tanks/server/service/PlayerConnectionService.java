package org.game.tanks.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.network.model.Handshake;
import org.game.tanks.network.model.command.ChatHistory;
import org.game.tanks.network.model.command.GameInitData;
import org.game.tanks.network.model.command.SyncTime;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerNetworkAdapter;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class PlayerConnectionService {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  private List<Long> generatedIds;
  private long idCount;

  @PostConstruct
  public void init() {
    generatedIds = new ArrayList<>();
    idCount = 0;
  }

  public void initializeNewPlayerConnection(Connection connection) {
    long newId = generateNextId();
    PlayerServerModel player = new PlayerServerModel(newId, connection);
    Handshake handshake = new Handshake()
        .setConnId(connection.getID())
        .setPlayerId(newId)
        .setPlayerName("");

    //Send handshake and initialization commands to player
    networkAdapter.sendTCP(connection, handshake);
    networkAdapter.sendTCP(connection, createGameInitData());
    networkAdapter.sendTCP(connection, new ChatHistory().setChatHistory(ctx.getChatHistory()));
    networkAdapter.sendTCP(connection, createSyncTimeCommand());

    // Send game progress info
    // Send players info
    // Send stats info

    // Broadcast to all about new incoming player

    ctx.getPendingPlayers().add(player);
  }

  public void removePlayerConnection(Connection connection) {
    PlayerServerModel player = getPlayerByConnectionId(connection.getID());
    if (player != null) {
      // TODO finalize connection with player - Send some packets related with disconnecting
      ctx.getLeavingPlayerIds().add(player.getPlayerId());
      generatedIds.remove(player.getPlayerId());
    }
  }


  public void addNewPlayerToTheGame(Handshake handshake) {
    ctx.getPendingPlayers()
    
    //TODO set player name handshake.getPlayerName()
  }

  private SyncTime createSyncTimeCommand() {
    SyncTime syncTime = new SyncTime()
        .setMatchEndTime(ctx.getMatchEndTime())
        .setMatchStartTime(ctx.getMatchStartTime())
        .setRoundEndTime(ctx.getRoundEndTime());
    return syncTime;
  }

  private GameInitData createGameInitData() {
    GameInitData data = new GameInitData();
    data.setCurrentMap(MapService.createMapInfoData(ctx.getCurrentMap()));
    data.setNextMap(MapService.createMapInfoData(ctx.getNextMap()));

    return data;
  }

  private PlayerServerModel getPlayerByConnectionId(int connectionId) {
    for (PlayerServerModel player : ctx.getPlayers()) {
      if (player.getConnection().getID() == connectionId) {
        return player;
      }
    }
    return null;
  }

  private long generateNextId() {
    while (generatedIds.contains(idCount)) {
      idCount++;
    }
    return idCount;
  }

}
