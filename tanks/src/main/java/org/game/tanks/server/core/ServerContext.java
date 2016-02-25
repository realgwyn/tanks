package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
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
  private List<PlayerServerModel> players;
  private HashMap<Long, PlayerServerModel> playerById;
  private ConcurrentLinkedQueue<PlayerServerModel> incomingPlayers;
  private ConcurrentLinkedQueue<Long> leavingPlayerIds;
  private ConcurrentLinkedQueue<PlayerSnapshot> incomingPlayerSnapshots;
  private ConcurrentLinkedQueue<GameEvent> incomingGameEvents;
  private ConcurrentLinkedQueue<GameEvent> outgoingGameEvents;
  private ConcurrentLinkedQueue<Command> incomingCommands;
  private ConcurrentLinkedQueue<Command> outgoingCommands;
  private ConcurrentLinkedQueue<CommunicationMessage> incomingMessages;
  private ConcurrentLinkedQueue<CommunicationMessage> outgoingMessages;
  private MapModel currentMap;
  private MapModel nextMap;

  @PostConstruct
  public void init() {
    players = new ArrayList<>();
    playerById = new HashMap<>();
    incomingPlayers = new ConcurrentLinkedQueue<>();
    leavingPlayerIds = new ConcurrentLinkedQueue<>();
    incomingPlayerSnapshots = new ConcurrentLinkedQueue<>();
    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();
    incomingCommands = new ConcurrentLinkedQueue<>();
    outgoingCommands = new ConcurrentLinkedQueue<>();
    incomingMessages = new ConcurrentLinkedQueue<>();
    outgoingMessages = new ConcurrentLinkedQueue<>();
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

  public ConcurrentLinkedQueue<Command> getIncomingCommands() {
    return incomingCommands;
  }

  public ConcurrentLinkedQueue<Command> getOutgoingCommands() {
    return outgoingCommands;
  }

  public ConcurrentLinkedQueue<CommunicationMessage> getIncomingCommunicationMessages() {
    return incomingMessages;
  }

  public ConcurrentLinkedQueue<CommunicationMessage> getOutgoingCommunicationMessages() {
    return outgoingMessages;
  }

  public ConcurrentLinkedQueue<Long> getLeavingPlayerIds() {
    return leavingPlayerIds;
  }

  public List<PlayerServerModel> getPlayers() {
    return players;
  }

  public void addPlayer(PlayerServerModel newPlayer) {
    players.add(newPlayer);
    playerById.put(newPlayer.getPlayerId(), newPlayer);
  }

  public PlayerServerModel getPlayerById(long id) {
    return playerById.get(id);
  }

  public void removePlayer(long playerId) {
    Iterator<PlayerServerModel> it = players.iterator();
    while (it.hasNext()) {
      PlayerServerModel player = it.next();
      if (player.getPlayerId() == playerId) {
        players.remove(player);
        break;
      }
    }

    playerById.remove(playerId);
  }

}
