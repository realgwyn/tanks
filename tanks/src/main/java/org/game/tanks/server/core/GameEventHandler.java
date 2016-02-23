package org.game.tanks.server.core;

import org.game.tanks.network.ServerNetworkAdapter;
import org.game.tanks.network.TCPListener;
import org.game.tanks.network.UDPListener;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.game.tanks.network.model.udp.GameSnapshot;
import org.game.tanks.network.model.udp.PlayerPosition;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class GameEventHandler implements UDPListener, TCPListener {

  @Autowired
  private ServerContext context;
  @Autowired
  private ServerNetworkAdapter network;

  //TODO: performance - if performance is slow, develop GameEvents process Scheduler and split GameEvent processes into priorities
  //right now all GameEvents are in equal priority - find out a way to make sure that method processHighPriorityEvents() 
  //will be executed every 10 ms, no matter how much time other methods will take time - this might require change in Game Loop method itself
  public void processGameEvents() {
    processHighPriorityEvents();
    processMediumPriorityEvents();
    processLowPriorityEvents();
  }


  //TODO: performance - if performance is slow, develop GameEvents process Scheduler and split GameEvent processes into priorities
  //right now all GameEvents are in equal priority - find out a way to make sure that method processHighPriorityEvents() 
  //will be executed every 10 ms, no matter how much time other methods will take time - this might require change in Game Loop method itself
  public void sendOutGameEvents() {
    sendOutHighPriorityEvents();
    sendOutMediumPriorityEvents();
    sendOutLowPriorityEvents();
  }

  /**
   * Player movements, Collisions and PvP actions
   */
  private void processHighPriorityEvents() {
    while (!context.getIncomingPlayerSnapshots().isEmpty()) {
      updatePlayerPosition(context.getIncomingPlayerSnapshots().poll());
    }
  }

  /**
   * Game Commands
   */
  private void processMediumPriorityEvents() {

  }

  /**
   * Chat Messages
   */
  private void processLowPriorityEvents() {

  }

  /**
   * GameSnapshots, Collisions and PvP actions
   */
  private void sendOutHighPriorityEvents() {
    network.getServer().sendToAllUDP(generateGameSnapshot());
  }

  /**
   * Game Commands, Player Statistics
   */
  private void sendOutMediumPriorityEvents() {

  }

  /**
   * Chat Messages, Server Commands
   */
  private void sendOutLowPriorityEvents() {

  }

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage message) {
    System.out.println("<TCP(id:" + conn.getID() + ")");
  }

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage message) {
    System.out.println("<UDP(id:" + conn.getID() + ")");
    if (message instanceof PlayerSnapshot) {
      context.getIncomingPlayerSnapshots().add((PlayerSnapshot) message);
    }
  }

  private GameSnapshot generateGameSnapshot() {
    GameSnapshot gameSnapshot = new GameSnapshot();
    if (gameSnapshot.sequenceNumber == Long.MAX_VALUE) {
      gameSnapshot.sequenceFlipFlag = !gameSnapshot.sequenceFlipFlag;
    }
    gameSnapshot.sequenceNumber++;

    gameSnapshot.positions = new PlayerPosition[context.getPlayers().size()];
    return gameSnapshot;
  }

  private void updatePlayerPosition(PlayerSnapshot snapshot) {
    for (PlayerServerModel player : context.getPlayers()) {
      if (player.getConnection().getID() == snapshot.id) {
        if (player.getSequenceNumber() < snapshot.sequenceNumber
            || player.sequenceFlipFlag != snapshot.sequenceFlipFlag) { // sequenceFlipFlag indicates that sequenceNumber exceeded long.MAX_VALUE
          player.sequenceFlipFlag = snapshot.sequenceFlipFlag;
          player.sequenceNumber = snapshot.sequenceNumber;
          player.playerPosition.x = snapshot.x;
          player.playerPosition.y = snapshot.y;
          player.playerPosition.bodyAngle = snapshot.bodyAngle;
          player.playerPosition.towerAngle = snapshot.towerAngle;
        }
        break;
      }
    }
  }

}
