package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.model.MapModel;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.Handshake;
import org.game.tanks.network.model.command.PlayerInfo;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.core.task.Task;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.stereotype.Component;

@Component
public class ServerContext {

  private List<PlayerServerModel> players;
  private List<PlayerServerModel> pendingPlayers;
  private Map<Long, PlayerServerModel> playerById;
  private Map<Long, PlayerInfo> playerStats;

  private ConcurrentLinkedQueue<Handshake> incomingHandshakes;
  private ConcurrentLinkedQueue<PlayerServerModel> incomingPlayers;
  private ConcurrentLinkedQueue<Long> leavingPlayerIds;

  private ConcurrentLinkedQueue<PlayerSnapshot> incomingPlayerSnapshots;

  private ConcurrentLinkedQueue<GameEvent> incomingGameEvents;
  private ConcurrentLinkedQueue<GameEvent> outgoingGameEvents;

  private ConcurrentLinkedQueue<Command> incomingCommands;
  private ConcurrentLinkedQueue<Command> outgoingCommands;

  private ConcurrentLinkedQueue<CommunicationMessage> incomingMessages;
  private ConcurrentLinkedQueue<CommunicationMessage> outgoingMessages;

  private ConcurrentLinkedQueue<AdminCommand> incomingAdminCommands;

  private List<ChatMessage> chatHistory;
  private ConcurrentLinkedQueue<Task> pendingTasks;

  private int tcpPort;
  private int udpPort;
  private String serverName;
  private MapModel currentMap;
  private MapModel nextMap;
  private long matchStartTime;
  private long matchEndTime;
  private long roundEndTime;

  /**
   * Used when starting the Server
   */
  @PostConstruct
  public void init() {
    pendingPlayers = new ArrayList<>();
    incomingHandshakes = new ConcurrentLinkedQueue<>();

    initDefaultValues();
    initValuesFromConfig();
    initCollections();
  }

  private void initCollections() {
    players = new ArrayList<>();
    playerById = new HashMap<>();
    leavingPlayerIds = new ConcurrentLinkedQueue<>();
    incomingPlayers = new ConcurrentLinkedQueue<>();
    incomingPlayerSnapshots = new ConcurrentLinkedQueue<>();

    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();
    incomingAdminCommands = new ConcurrentLinkedQueue<>();
    incomingMessages = new ConcurrentLinkedQueue<>();
    outgoingMessages = new ConcurrentLinkedQueue<>();
    incomingCommands = new ConcurrentLinkedQueue<>();
    outgoingCommands = new ConcurrentLinkedQueue<>();
    currentMap = new MapModel();
    nextMap = new MapModel();
    playerStats = new HashMap<>();
    chatHistory = new ArrayList<>();
    pendingTasks = new ConcurrentLinkedQueue<>();
  }

  private void initDefaultValues() {
    private int tcpPort = 55555;
    private int udpPort = 55556;
    private String serverName = "Undefined";
    private MapModel currentMap = new MapModel();
    private MapModel nextMap = new MapModel();
    private long matchStartTime = 0;
    private long matchEndTime = 0;
    private long roundEndTime = 0;
  }

  private void initValuesFromConfig() {

  }

  /**
   * Used when starting new Match
   */
  public void reinitialize() {
    //clear all data objects
    //clear current player list
    //handshake all players again

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

  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
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

  public ConcurrentLinkedQueue<AdminCommand> getIncomingAdminCommands() {
    return incomingAdminCommands;
  }

  public ConcurrentLinkedQueue<Task> getPendingTasks() {
    return pendingTasks;
  }

  public ConcurrentLinkedQueue<Handshake> getIncomingHandshakes() {
    return incomingHandshakes;
  }

  public List<PlayerServerModel> getPendingPlayers() {
    return pendingPlayers;
  }

  public ConcurrentLinkedQueue<Long> getLeavingPlayerIds() {
    return leavingPlayerIds;
  }

  public List<PlayerServerModel> getPlayers() {
    return players;
  }

  public Map<Long, PlayerInfo> getPlayerStatsAll() {
    return playerStats;
  }

  public List<ChatMessage> getChatHistory() {
    return chatHistory;
  }

  public long getMatchStartTime() {
    return matchStartTime;
  }

  public ServerContext setMatchStartTime(long matchStartTime) {
    this.matchStartTime = matchStartTime;
    return this;
  }

  public long getMatchEndTime() {
    return matchEndTime;
  }

  public ServerContext setMatchEndTime(long matchEndTime) {
    this.matchEndTime = matchEndTime;
    return this;
  }

  public long getRoundEndTime() {
    return roundEndTime;
  }

  public ServerContext setRoundEndTime(long roundEndTime) {
    this.roundEndTime = roundEndTime;
    return this;
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
    playerStats.remove(playerId);
  }

}
