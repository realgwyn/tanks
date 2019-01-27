package io.game.tanks.server.core.process;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.model.game.PlayerModel;
import io.game.tanks.network.model.udp.GameSnapshot;
import io.game.tanks.network.model.udp.PlayerPosition;
import io.game.tanks.network.model.udp.PlayerSnapshot;
import io.game.tanks.server.cfg.ServerConfig;
import io.game.tanks.server.core.ServerContext;
import io.game.tanks.server.core.ServerEngine;
import io.game.tanks.server.core.ServerEventBus;
import io.game.tanks.server.core.ServerNetworkAdapter;
import io.game.tanks.server.model.PlayerServerModel;
import io.tanks.common.core.utils.GraphicsUtils;

/**
 * Processes Player movements
 */
@Component
public class PlayerMovementsHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerEventBus bus;
  @Autowired
  SchedulerContext schedulerCtx;
  @Autowired
  ServerEngine engine;
  @Autowired
  ServerConfig config;
  @Autowired
  ServerNetworkAdapter networkAdapter;

  private boolean playerPositionCorrectionEnabled;

  @Override
  public void runProcess() {
    updatePlayerPositions();
    playerPositionsCorrection();
    sendOutGameSnapshot();
  }

  @PostConstruct
  public void init() {
    playerPositionCorrectionEnabled = config.getPropertyBoolean(ServerConfig.SERVER_ENABLE_PLAYER_POSITION_CORRECTION);
  }

  private void updatePlayerPositions() {
    while (!bus.getIncomingPlayerSnapshots().isEmpty()) {
      updatePlayerPosition(bus.getIncomingPlayerSnapshots().poll());
    }
  }

  private void updatePlayerPosition(PlayerSnapshot snapshot) {
    for (PlayerServerModel player : schedulerCtx.getPlayers()) {
      if (player.getConnectionId() == snapshot.playerId) {
        // sequenceFlipFlag indicates that sequenceNumber exceeded long.MAX_VALUE
        PlayerModel model = player.getModel();
        if (player.getSequenceNumber() < snapshot.sequenceNumber || player.sequenceFlipFlag != snapshot.sequenceFlipFlag) {
          player.sequenceFlipFlag = snapshot.sequenceFlipFlag;
          player.sequenceNumber = snapshot.sequenceNumber;
          model.recentX = model.x;
          model.recentY = model.y;
          model.x = snapshot.x;
          model.y = snapshot.y;
          model.bodyAngle = snapshot.bodyAngle;
          model.towerAngle = snapshot.towerAngle;
          model.shape = GraphicsUtils.updatePlayerShape(model.shape, snapshot.x, snapshot.y, snapshot.bodyAngle,
              snapshot.towerAngle);
        }
        break;
      }
    }
  }

  /**
   * TODO: To avoid double looping, should split map into sectors and run positionCorrection only against players inside
   * each sector and with adjacent ones
   */
  private void playerPositionsCorrection() {
    List<PlayerServerModel> players = schedulerCtx.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      for (int j = 0; j < players.size(); j++) {
        if (i == j) {
          continue;
        }
        PlayerModel p1 = players.get(i).getModel();
        PlayerModel p2 = players.get(j).getModel();
        if (playerPositionCorrectionEnabled && p1.getShape().getBounds2D().intersects(p2.getShape().getBounds2D())) {
          // TODO implement recursive function for re-checking collisions with other players after changed position
          // This solution is naive, it just moves back the player, not checking if behind him is other player coliding
          correctPosition(p1, p2);
        }
      }
    }
  }

  private void correctPosition(PlayerModel p1, PlayerModel p2) {
    float xScalar = p1.recentX - p1.x;
    float yScalar = p1.recentY - p1.y;

    p1.x += xScalar;
    p1.y += yScalar;

    while (p1.getShape().getBounds2D().intersects(p2.getShape().getBounds2D())) {
      p1.x += xScalar;
      p1.y += yScalar;
    }
  }

  private void sendOutGameSnapshot() {
    networkAdapter.sendToAllUDP(generateGameSnapshot());
  }

  private GameSnapshot generateGameSnapshot() {
    GameSnapshot gameSnapshot = new GameSnapshot();
    if (gameSnapshot.sequenceNumber == Long.MAX_VALUE) {
      gameSnapshot.sequenceFlipFlag = !gameSnapshot.sequenceFlipFlag;
    }
    gameSnapshot.sequenceNumber++;

    gameSnapshot.positions = new PlayerPosition[schedulerCtx.getPlayers().size()];
    for (int i = 0; i < schedulerCtx.getPlayers().size(); i++) {
      PlayerServerModel player = schedulerCtx.getPlayers().get(i);
      PlayerPosition playerPosition = new PlayerPosition();
      playerPosition.playerId = player.getConnectionId();
      playerPosition.x = player.getModel().x;
      playerPosition.y = player.getModel().y;
      playerPosition.bodyAngle = player.getModel().bodyAngle;
      playerPosition.towerAngle = player.getModel().towerAngle;
      gameSnapshot.positions[i] = playerPosition;
    }
    return gameSnapshot;
  }

}