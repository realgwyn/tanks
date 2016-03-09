package org.game.tanks.server.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.Handshake;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.stereotype.Component;

/**
 * Thread-safe event writing/reading between threads
 * 
 * @author rafal.kojta
 */
@Component
public class EventBus {

  private Queue<Handshake> incomingHandshakes;
  private Queue<PlayerServerModel> incomingPlayers;
  private Queue<Integer> leavingPlayerIds;
  private Queue<AdminCommand> incomingAdminCommands;

  private Queue<PlayerSnapshot> incomingPlayerSnapshots;

  private Queue<GameEvent> incomingGameEvents;
  private Queue<GameEvent> outgoingGameEvents;

  private Queue<Command> incomingCommands;
  private Queue<Command> outgoingCommands;

  private Queue<CommunicationMessage> incomingMessages;
  private Queue<CommunicationMessage> outgoingMessages;

  @PostConstruct
  public void init() {
    incomingHandshakes = new ConcurrentLinkedQueue<>();
    incomingAdminCommands = new ConcurrentLinkedQueue<>();
    leavingPlayerIds = new ConcurrentLinkedQueue<>();
    incomingPlayers = new ConcurrentLinkedQueue<>();

    flushEvents();
  }

  public void flushEvents() {
    incomingPlayerSnapshots = new ConcurrentLinkedQueue<>();

    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();

    incomingMessages = new ConcurrentLinkedQueue<>();
    outgoingMessages = new ConcurrentLinkedQueue<>();

    incomingCommands = new ConcurrentLinkedQueue<>();
    outgoingCommands = new ConcurrentLinkedQueue<>();
  }

  public Queue<PlayerServerModel> getIncomingPlayers() {
    return incomingPlayers;
  }

  public Queue<GameEvent> getIncomingGameEvents() {
    return incomingGameEvents;
  }

  public Queue<GameEvent> getOutgoingGameEvents() {
    return outgoingGameEvents;
  }

  public Queue<PlayerSnapshot> getIncomingPlayerSnapshots() {
    return incomingPlayerSnapshots;
  }

  public Queue<Command> getIncomingCommands() {
    return incomingCommands;
  }

  public Queue<Command> getOutgoingCommands() {
    return outgoingCommands;
  }

  public Queue<CommunicationMessage> getIncomingCommunicationMessages() {
    return incomingMessages;
  }

  public Queue<CommunicationMessage> getOutgoingCommunicationMessages() {
    return outgoingMessages;
  }

  public Queue<AdminCommand> getIncomingAdminCommands() {
    return incomingAdminCommands;
  }

  public Queue<Handshake> getIncomingHandshakes() {
    return incomingHandshakes;
  }

  public Queue<Integer> getLeavingPlayerIds() {
    return leavingPlayerIds;
  }

}
