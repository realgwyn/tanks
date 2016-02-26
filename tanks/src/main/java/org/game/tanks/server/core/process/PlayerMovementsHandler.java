package org.game.tanks.server.core.process;

import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.network.model.udp.GameSnapshot;
import org.game.tanks.network.model.udp.PlayerPosition;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.ServerNetworkAdapter;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.utils.GraphicsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Processes Player movements
 */
@Component
public class PlayerMovementsHandler extends ScheduledProcess {

  @Autowired
  ServerContext ctx;
  @Autowired
  ServerNetworkAdapter networkAdapter;
  @Autowired
  ServerEngine engine;
  @Autowired
  Config config;

  private boolean playerPositionCorrectionEnabled;

  @Override
  public void runProcess() {
    updatePlayerPositions();
    playerPositionsCorrection();
    sendOutGameSnapshot();
  }

  @PostConstruct
  public void init() {
    playerPositionCorrectionEnabled = config.getPropertyBoolean(Config.SERVER_ENABLE_PLAYER_POSITION_CORRECTION);
  }

  private void updatePlayerPositions() {
    while (!ctx.getIncomingPlayerSnapshots().isEmpty()) {
      updatePlayerPosition(ctx.getIncomingPlayerSnapshots().poll());
    }
  }

  private void updatePlayerPosition(PlayerSnapshot snapshot) {
    for (PlayerServerModel player : ctx.getPlayers()) {
      if (player.getConnection().getID() == snapshot.id) {
        // sequenceFlipFlag indicates that sequenceNumber exceeded long.MAX_VALUE
        if (player.getSequenceNumber() < snapshot.sequenceNumber || player.sequenceFlipFlag != snapshot.sequenceFlipFlag) {
          player.sequenceFlipFlag = snapshot.sequenceFlipFlag;
          player.sequenceNumber = snapshot.sequenceNumber;
          player.recentX = player.x;
          player.recentY = player.y;
          player.x = snapshot.x;
          player.y = snapshot.y;
          player.bodyAngle = snapshot.bodyAngle;
          player.towerAngle = snapshot.towerAngle;
          player.shape = GraphicsUtils.updatePlayerShape(player.shape, snapshot.x, snapshot.y, snapshot.bodyAngle,
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
    List<PlayerServerModel> players = ctx.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      for (int j = 0; j < players.size(); j++) {
        if (i == j) {
          continue;
        }
        PlayerServerModel p1 = players.get(i);
        PlayerServerModel p2 = players.get(j);
        if (playerPositionCorrectionEnabled && p1.getShape().getBounds2D().intersects(p2.getShape().getBounds2D())) {
          // TODO implement recursive function for re-checking collisions with other players after changed position
          // This solution is naive, it just moves back the player, not checking if behind him is other player coliding
          correctPosition(p1, p2);
        }
      }
    }
  }

  private void correctPosition(PlayerServerModel p1, PlayerServerModel p2) {
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
    networkAdapter.getServer().sendToAllUDP(generateGameSnapshot());
  }

  private GameSnapshot generateGameSnapshot() {
    GameSnapshot gameSnapshot = new GameSnapshot();
    if (gameSnapshot.sequenceNumber == Long.MAX_VALUE) {
      gameSnapshot.sequenceFlipFlag = !gameSnapshot.sequenceFlipFlag;
    }
    gameSnapshot.sequenceNumber++;

    gameSnapshot.positions = new PlayerPosition[ctx.getPlayers().size()];
    gameSnapshot.state = engine.getState().getType();
    return gameSnapshot;
  }

}