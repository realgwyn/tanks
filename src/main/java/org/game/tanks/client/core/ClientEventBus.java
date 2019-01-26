package org.game.tanks.client.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.udp.GameSnapshot;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.springframework.stereotype.Component;

@Component
public class ClientEventBus {

  private Queue<GameSnapshot> incomingGameSnapshots;
  private Queue<PlayerSnapshot> outgoingPlayerSnapshots;

  private Queue<GameEvent> incomingGameEvents;
  private Queue<GameEvent> outgoingGameEvents;

  private Queue<Command> incomingCommands;
  private Queue<Command> outgoingCommands;
  private Queue<Command> defferedIncomingCommands;

  private Queue<CommunicationMessage> incomingMessages;
  private Queue<CommunicationMessage> outgoingMessages;

  @PostConstruct()
  public void init() {
    flushEvents();
  }

  public void flushEvents() {
    incomingGameSnapshots = new ConcurrentLinkedQueue<>();
    outgoingPlayerSnapshots = new ConcurrentLinkedQueue<>();

    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();

    incomingMessages = new ConcurrentLinkedQueue<>();
    outgoingMessages = new ConcurrentLinkedQueue<>();

    defferedIncomingCommands = new ConcurrentLinkedQueue<>();
    incomingCommands = new ConcurrentLinkedQueue<>();
    outgoingCommands = new ConcurrentLinkedQueue<>();
  }

  public Queue<GameSnapshot> getIncomingGameSnapshots() {
    return incomingGameSnapshots;
  }

  public Queue<PlayerSnapshot> getOutgoingPlayerSnapshots() {
    return outgoingPlayerSnapshots;
  }

  public Queue<GameEvent> getIncomingGameEvents() {
    return incomingGameEvents;
  }

  public Queue<GameEvent> getOutgoingGameEvents() {
    return outgoingGameEvents;
  }

  public Queue<Command> getIncomingCommands() {
    return incomingCommands;
  }

  public Queue<Command> getOutgoingCommands() {
    return outgoingCommands;
  }

  public Queue<Command> getDefferedIncomingCommands() {
    return defferedIncomingCommands;
  }

  public Queue<CommunicationMessage> getIncomingMessages() {
    return incomingMessages;
  }

  public Queue<CommunicationMessage> getOutgoingMessages() {
    return outgoingMessages;
  }

}
