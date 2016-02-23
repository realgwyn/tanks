package org.game.tanks.server.core;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.model.MapModel;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.stereotype.Component;

@Component
public class ServerContext {

  private int tcpPort;
  private int udpPort;
  private int serverName;
  private ConcurrentLinkedQueue<PlayerServerModel> players;
  private ConcurrentLinkedQueue<PlayerServerModel> incomingPlayers;
  private ConcurrentLinkedQueue<PlayerSnapshot> incomingPlayerSnapshots;
  private ConcurrentLinkedQueue<GameEvent> incomingGameEvents;
  private ConcurrentLinkedQueue<GameEvent> outgoingGameEvents;
  private MapModel currentMap;
  private MapModel nextMap;

  @PostConstruct
  public void init() {
    players = new ConcurrentLinkedQueue<>();
    incomingPlayers = new ConcurrentLinkedQueue<>();
    incomingPlayerSnapshots = new ConcurrentLinkedQueue<>();
    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();
    currentMap = new MapModel();
    nextMap = new MapModel();
  }

  public MapModel getCurrentMap() {
    return currentMap;
  }

  public void setCurrentMap(MapModel currentMap) {
    this.currentMap = currentMap;
  }

  public MapModel getNextMap() {
    return nextMap;
  }

  public void setNextMap(MapModel nextMap) {
    this.nextMap = nextMap;
  }

  public int getTcpPort() {
    return tcpPort;
  }

  public void setTcpPort(int tcpPort) {
    this.tcpPort = tcpPort;
  }

  public int getUdpPort() {
    return udpPort;
  }

  public void setUdpPort(int udpPort) {
    this.udpPort = udpPort;
  }

  public int getServerName() {
    return serverName;
  }

  public void setServerName(int serverName) {
    this.serverName = serverName;
  }

  public ConcurrentLinkedQueue<PlayerServerModel> getIncomingPlayers() {
    return incomingPlayers;
  }

  public ConcurrentLinkedQueue<GameEvent> getIncomingGameEvents() {
    return incomingGameEvents;
  }

  public ConcurrentLinkedQueue<GameEvent> getOutgoingGameEvents() {
    return outgoingGameEvents;
  }

  public ConcurrentLinkedQueue<PlayerSnapshot> getIncomingPlayerSnapshots() {
    return incomingPlayerSnapshots;
  }

  public ConcurrentLinkedQueue<PlayerServerModel> getPlayers() {
    return players;
  }

}
