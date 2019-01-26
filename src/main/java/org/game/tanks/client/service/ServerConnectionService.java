package org.game.tanks.client.service;

import org.game.tanks.client.core.ClientContext;
import org.game.tanks.client.core.ClientEventBus;
import org.game.tanks.client.core.ClientNetworkAdapter;
import org.game.tanks.client.model.PlayerGameModel;
import org.game.tanks.network.ConnectionAddress;
import org.game.tanks.network.NetworkException;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.command.GameInitData;
import org.game.tanks.network.model.command.Handshake;
import org.game.tanks.network.model.command.PlayerInfo;
import org.game.tanks.network.model.command.SyncTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerConnectionService {

  @Autowired
  ClientContext ctx;
  @Autowired
  ClientEventBus bus;
  @Autowired
  ClientNetworkAdapter networkAdapter;

  private final int MATCH_INIT_FINISHED = 0x111;
  private final int HANDSHAKE = 0x001;
  private final int GAME_INIT_DATA = 0x010;
  private final int SYNC_TIME_DATA = 0x100;
  private int connectionStatusFlag = 0x000;
  private boolean connectionReady = false;

  public void connectToServer(ConnectionAddress addr) throws NetworkException {
    networkAdapter.connectToServer(addr);
  }

  public void disconnect() {
    networkAdapter.disconnect();
  }

  public void processMatchInitCommands() {
    connectionReady = false;
    while (!bus.getIncomingCommands().isEmpty()) {
      Command cmd = bus.getIncomingCommands().poll();
      if (cmd instanceof GameInitData) {
        processGameInitData((GameInitData) cmd);
        connectionStatusFlag |= GAME_INIT_DATA;
      } else if (cmd instanceof SyncTime) {
        processSyncTime((SyncTime) cmd);
        connectionStatusFlag |= SYNC_TIME_DATA;
      } else if (cmd instanceof Handshake) {
        processHandshake((Handshake) cmd);
        connectionStatusFlag |= HANDSHAKE;
      } else {
        // Deffer all other commands in this stage and consume them later
        bus.getDefferedIncomingCommands().add(cmd);
      }

      if (connectionStatusFlag == MATCH_INIT_FINISHED) {
        connectionReady = true;
        return;
      }
    }
  }

  public boolean isConnectionReady() {
    return connectionReady;
  }

  private void processHandshake(Handshake handshake) {
    ctx.setPlayerId(handshake.getPlayerConnectionId());
    Handshake handshakeAck = new Handshake()
        .setPlayerConnectionId(handshake.getPlayerConnectionId())
        .setPlayerName(ctx.getPlayerName());
    networkAdapter.sendTCP(handshakeAck);
  }

  private void processGameInitData(GameInitData data) {
    for (PlayerInfo info : data.getPlayersInfo()) {
      PlayerGameModel player = new PlayerGameModel();
      // TODO: init player properties
      ctx.addPlayer(player);
    }
  }

  private void processSyncTime(SyncTime syncTime) {
    ctx.setMatchEndTime(syncTime.getMatchEndTime());
    ctx.setMatchStartTime(syncTime.getMatchStartTime());
    ctx.setRoundEndTime(syncTime.getRoundEndTime());
  }

}
