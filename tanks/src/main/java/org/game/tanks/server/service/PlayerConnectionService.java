package org.game.tanks.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.network.model.Handshake;
import org.game.tanks.network.model.command.ChatHistory;
import org.game.tanks.network.model.command.Connect;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.GameInitData;
import org.game.tanks.network.model.command.PlayerInfo;
import org.game.tanks.network.model.message.ChatMessage;
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
  @Autowired
  SyncTimeService syncTimeService;

  private List<Long> generatedIds;
  private long idCount;

  @PostConstruct
  public void init() {
    generatedIds = new ArrayList<>();
    idCount = 0;
  }

  /**
   * Entry point for new Player Connection
   */
  public void initializeNewPlayerConnection(Connection connection) {
    long newId = generateNextId();
    PlayerServerModel player = new PlayerServerModel(newId, connection);
    Handshake handshake = new Handshake()
        .setConnId(connection.getID())
        .setPlayerId(newId)
        .setPlayerName("");

    // Send handshake and initialization commands to player
    networkAdapter.sendTCP(connection, handshake);
    networkAdapter.sendTCP(connection, createGameInitData());
    networkAdapter.sendTCP(connection, new ChatHistory().setChatHistory(ctx.getChatHistory().toArray(new ChatMessage[0])));
    networkAdapter.sendTCP(connection, syncTimeService.createNewSyncTimeEvent());

    ctx.getPendingPlayers().add(player);
  }

  /**
   * When new Player Connection sends back ACK handshake
   */
  public void addNewPlayerToTheGame(Handshake handshake) {
    for (PlayerServerModel player : ctx.getPendingPlayers()) {
      if (player.getPlayerId() == handshake.getPlayerId()) {
        player.setPlayerName(handshake.getPlayerName());
        Connect connect = new Connect()
            .setPlayerId(player.getPlayerId())
            .setPlayerName(player.getPlayerName());
        // Broadcast to other players about new connection
        networkAdapter.sendToAllTCP(connect);
        ctx.getIncomingPlayers().add(player);
        break;
      }
    }
  }

  public GameInitData createGameInitData() {
    GameInitData data = new GameInitData()
        .setPlayersInfo(createPlayersInfo(ctx.getPlayers()))// XXX concurrent modification exception
        .setMatchEndTime(ctx.getMatchEndTime())
        .setRoundEndTime(ctx.getRoundEndTime())
        .setCurrentMap(MapService.createMapInfoData(ctx.getCurrentMap()))
        .setNextMap(MapService.createMapInfoData(ctx.getNextMap()));

    return data;
  }

  public List<PlayerInfo> createPlayersInfo(List<PlayerServerModel> players) {
    List<PlayerInfo> playersInfo = new ArrayList<>();
    for (PlayerServerModel player : players) {

    }
    return playersInfo;
  }

  /**
   * When Player Connection ended
   */
  public void removePlayerConnection(Connection connection) {
    PlayerServerModel player = getPlayerByConnectionId(connection.getID());
    if (player != null) {
      Disconnect disconnect = new Disconnect()
          .setPlayerId(player.getPlayerId());
      networkAdapter.sendToAllTCP(disconnect);
      ctx.getLeavingPlayerIds().add(player.getPlayerId());
      generatedIds.remove(player.getPlayerId());
    }
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
    idCount++;
    while (generatedIds.contains(idCount)) {
      idCount++;
    }
    return idCount;
  }

  // public static void main(String[] args) {
  // List<String> players = new ArrayList<>();
  //
  // Runnable thread1 = new Runnable() {
  //
  // @Override
  // public void run() {
  // while (true) {
  // players.add("S");
  // try {
  // Thread.sleep(5);
  // } catch (InterruptedException e) {
  // e.printStackTrace();
  // }
  // }
  //
  // }
  // };
  //
  // Gson gson = new Gson();
  //
  // Runnable thread2 = new Runnable() {
  //
  // @Override
  // public void run() {
  // int count = 0;
  // while (true) {
  // count++;
  // try {
  // String json = gson.toJson(l.toArray());
  // if (count % 100 == 0) {
  // System.out.println(json);
  // }
  //
  // Thread.sleep(3);
  // } catch (InterruptedException e) {
  // e.printStackTrace();
  // }
  // }
  // }
  // };
  //
  // new Thread(thread1).start();
  // new Thread(thread2).start();
  //
  // }

}
