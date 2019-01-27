package io.tanks.server.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import io.tanks.common.network.model.AdminCommand;
import io.tanks.common.network.model.Command;
import io.tanks.common.network.model.CommunicationMessage;
import io.tanks.common.network.model.GameEvent;
import io.tanks.common.network.model.command.Handshake;
import io.tanks.common.network.model.udp.PlayerSnapshot;
import io.tanks.server.model.PlayerServerModel;
import org.springframework.stereotype.Component;

/**
 * Thread-safe event writing/reading between threads
 * 
 * @author rafal.kojta
 */
@Component
public class ServerEventBus {

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
