package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.game.tanks.model.MapModel;
import org.game.tanks.network.model.Handshake;
import org.game.tanks.network.model.command.Connect;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.GameInitData;
import org.game.tanks.network.model.command.PlayerInfo;
import org.game.tanks.server.model.ConnectionInfo;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.server.service.MapService;
import org.game.tanks.server.service.SyncStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerConnectionThread implements Runnable {

  private final static Logger logger = Logger.getLogger(PlayerConnectionThread.class);

  @Autowired
  ServerContext ctx;
  @Autowired
  EventBus bus;
  @Autowired
  ServerNetworkAdapter networkAdapter;
  @Autowired
  SyncStateService syncTimeService;

  private boolean running;
  private boolean paused;
  private boolean reconnectPlayers;
  private List<PlayerServerModel> pendingPlayers;
  private List<PlayerInfo> connectedPlayers;
  private MapModel currentMap;
  private MapModel nextMap;
  private boolean changeMap;

  @PostConstruct
  public void init() {
    pendingPlayers = new ArrayList<>();
    connectedPlayers = new ArrayList<>();
  }

  public synchronized void start() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    try {
      logger.debug("Running...");
      running = true;
      while (running) {
        while (!paused) {

          if (changeMap) {
            changeMap = false;
            currentMap = nextMap;
          }

          processPlayerConnections();

          if (reconnectPlayers) {
            reconnectPlayers = false;
            reinitializePlayersConnections();
          }

          Thread.sleep(5);
        }
        Thread.sleep(10);// Sleep a little bit
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void pause() {
    paused = true;
  }

  public void resume() {
    paused = false;
  }

  public void finish() {
    logger.debug("Stopping...");
    running = false;
  }

  public void reconnectPlayers() {
    reconnectPlayers = true;
  }

  public void setCurrentMap(MapModel map) {
    nextMap = map;
    changeMap = true;
  }

  public void processPlayerConnections() {
    processNewConnections();
    processIncomingHandshakes();
    processClosedConnections();

  }

  private void processNewConnections() {
    while (networkAdapter.hasNewConnections()) {
      ConnectionInfo connection = networkAdapter.pollNewConnection();
      initializeNewPlayerConnection(connection);
    }
  }

  private void processIncomingHandshakes() {
    while (!bus.getIncomingHandshakes().isEmpty()) {
      Handshake handshake = bus.getIncomingHandshakes().poll();
      addNewPlayerToTheGame(handshake);
    }
  }

  private void processClosedConnections() {
    while (networkAdapter.hasClosedConnections()) {
      Integer closedConnectionId = networkAdapter.pollClosedConnectionId();
      removePlayerConnection(closedConnectionId);
    }
  }

  protected void reinitializePlayersConnections() {
    networkAdapter.sendToAllTCP(createGameInitData());
    networkAdapter.sendToAllTCP(syncTimeService.createNewSyncTimeEvent());
  }

  /**
   * Entry point for new Player Connection
   */
  protected void initializeNewPlayerConnection(ConnectionInfo connectionInfo) {

    PlayerInfo info = new PlayerInfo(connectionInfo.getConnectionId());
    PlayerServerModel player = new PlayerServerModel(connectionInfo, info);
    Handshake handshake = new Handshake()
        .setPlayerConnectionId(player.getConnectionId())
        .setPlayerName("");

    // Send handshake and initialization commands to player
    networkAdapter.sendTCP(player.getConnectionId(), handshake);
    networkAdapter.sendTCP(player.getConnectionId(), createGameInitData());
    networkAdapter.sendTCP(player.getConnectionId(), syncTimeService.createNewSyncTimeEvent());

    pendingPlayers.add(player);
  }

  /**
   * When new Player Connection sends back ACK handshake
   */
  protected void addNewPlayerToTheGame(Handshake handshake) {
    Iterator<PlayerServerModel> it = pendingPlayers.iterator();
    while (it.hasNext()) {
      PlayerServerModel player = it.next();
      if (player.getConnectionId() == handshake.getPlayerConnectionId()) {
        player.setPlayerName(handshake.getPlayerName());
        Connect connect = new Connect()
            .setPlayerId(player.getConnectionId())
            .setPlayerName(player.getPlayerName());
        // Broadcast to other players about new connection
        networkAdapter.sendToAllTCP(connect);
        bus.getIncomingPlayers().add(player);
        connectedPlayers.add(player.getPlayerInfo());
        it.remove();
        return;
      }
    }
  }

  protected GameInitData createGameInitData() {
    GameInitData data = new GameInitData()
        .setPlayersInfo(connectedPlayers)
        .setMatchEndTime(ctx.getMatchEndTime())
        .setRoundEndTime(ctx.getRoundEndTime())
        .setCurrentMap(MapService.createMapInfoData(currentMap));

    return data;
  }

  /**
   * When Player Connection ended
   */
  protected void removePlayerConnection(Integer connectionId) {
    PlayerInfo player = getPlayerByConnectionId(connectionId);
    if (player != null) {
      Disconnect disconnect = new Disconnect()
          .setPlayerId(player.getPlayerId());
      networkAdapter.sendToAllTCP(disconnect);
      bus.getLeavingPlayerIds().add(player.getPlayerId());
      connectedPlayers.remove(player);
    }
  }

  protected PlayerInfo getPlayerByConnectionId(int connectionId) {
    for (PlayerInfo player : connectedPlayers) {
      if (player.getPlayerId() == connectionId) {
        return player;
      }
    }
    return null;
  }

}
