package org.game.tanks.server.core;

import org.game.tanks.network.TCPListener;
import org.game.tanks.network.UDPListener;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class GameEventHandler implements UDPListener, TCPListener {

  // private

  @Autowired
  private ServerContext context;

  public void processGameEvents() {
    while (!context.getIncomingGameEvents().isEmpty()) {
      GameEvent gameEvent = context.getIncomingGameEvents().poll();
    }

  }

  /**
   * Game Snapshot, Player movements, Player PvP actions
   */
  private void processHighPriorityEvents() {

  }

  /**
   * Game Commands, Player Statistics
   */
  private void processMediumPriorityEvents() {

  }

  /**
   * Chat Messages, Server Messages
   */
  private void processLowPriorityEvents() {

  }

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage request) {

  }

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage request) {

  }

}
