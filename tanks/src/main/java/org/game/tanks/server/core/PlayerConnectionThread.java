package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
  ServerNetworkAdapter networkAdapter;
  @Autowired
  SyncStateService syncTimeService;

  private boolean running;
  private boolean reconnectPlayers;
  private List<PlayerServerModel> pendingPlayers;
  private List<PlayerServerModel> connectedPlayers;

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
    logger.debug("Running...");
    running = true;
    while (running) {
      processPlayerConnections();
      try {
        Thread.sleep(10);// Sleep a little bit
      } catch (InterruptedException e) {
      }
    }
  }

  public void finish() {
    logger.debug("Stopping...");
    running = false;
  }

  public void reconnectPlayers() {
    reconnectPlayers = true;
  }

  public void processPlayerConnections() {

    processNewConnections();
    processIncomingHandshakes();
    processClosedConnections();
    if (reconnectPlayers) {
      reinitializePlayersConnections();
      reconnectPlayers = false;
    }
  }

  private void processNewConnections() {
    while (networkAdapter.hasNewConnections()) {
      ConnectionInfo connection = networkAdapter.pollNewConnection();
      initializeNewPlayerConnection(connection);
    }
  }

  private void processIncomingHandshakes() {
    while (!ctx.getIncomingHandshakes().isEmpty()) {
      Handshake handshake = ctx.getIncomingHandshakes().poll();
      addNewPlayerToTheGame(handshake);
    }
  }

  private void processClosedConnections() {
    while (networkAdapter.hasClosedConnections()) {
      Integer closedConnectionId = networkAdapter.pollClosedConnectionId();
      removePlayerConnection(closedConnectionId);
    }
  }

  /**
   * Entry point for new Player Connection
   */
  protected void initializeNewPlayerConnection(ConnectionInfo connectionInfo) {
    PlayerServerModel player = new PlayerServerModel(connectionInfo);
    Handshake handshake = new Handshake()
        .setPlayerConnectionId(player.getConnectionId())
        .setPlayerName("");

    // Send handshake and initialization commands to player
    networkAdapter.sendTCP(player.getConnectionId(), handshake);
    networkAdapter.sendTCP(player.getConnectionId(), createGameInitData());
    networkAdapter.sendTCP(player.getConnectionId(), syncTimeService.createNewSyncTimeEvent());

    pendingPlayers.add(player);
  }

  protected void reinitializePlayersConnections() {
    networkAdapter.sendToAllTCP(createGameInitData());
    networkAdapter.sendToAllTCP(syncTimeService.createNewSyncTimeEvent());
  }

  /**
   * When new Player Connection sends back ACK handshake
   */
  protected void addNewPlayerToTheGame(Handshake handshake) {
    for (PlayerServerModel player : pendingPlayers) {
      if (player.getConnectionId() == handshake.getPlayerConnectionId()) {
        player.setPlayerName(handshake.getPlayerName());
        Connect connect = new Connect()
            .setPlayerId(player.getConnectionId())
            .setPlayerName(player.getPlayerName());
        // Broadcast to other players about new connection
        networkAdapter.sendToAllTCP(connect);
        ctx.getIncomingPlayers().add(player);
        break;
      }
    }
  }

  protected GameInitData createGameInitData() {
    GameInitData data = new GameInitData()
        .setPlayersInfo(createPlayersInfo(connectedPlayers))
        .setMatchEndTime(ctx.getMatchEndTime())
        .setRoundEndTime(ctx.getRoundEndTime())
        .setCurrentMap(MapService.createMapInfoData(ctx.getCurrentMap()))
        .setNextMap(MapService.createMapInfoData(ctx.getNextMap()));

    return data;
  }

  protected List<PlayerInfo> createPlayersInfo(List<PlayerServerModel> players) {
    List<PlayerInfo> playersInfo = new ArrayList<>();
    for (PlayerServerModel player : players) {

    }
    return playersInfo;
  }

  /**
   * When Player Connection ended
   */
  protected void removePlayerConnection(Integer connectionId) {
    PlayerServerModel player = getPlayerByConnectionId(connectionId);
    if (player != null) {
      Disconnect disconnect = new Disconnect()
          .setPlayerId(player.getConnectionId());
      networkAdapter.sendToAllTCP(disconnect);
      ctx.getLeavingPlayerIds().add(player.getConnectionId());
    }
  }

  protected PlayerServerModel getPlayerByConnectionId(int connectionId) {
    for (PlayerServerModel player : connectedPlayers) {
      if (player.getConnectionId() == connectionId) {
        return player;
      }
    }
    return null;
  }

}
