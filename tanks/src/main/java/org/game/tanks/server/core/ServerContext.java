package org.game.tanks.server.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerContext {

  @Autowired
  Config cfg;

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

  private int matchDuration;
  private int roundDuration;
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
    incomingAdminCommands = new ConcurrentLinkedQueue<>();
    pendingTasks = new ConcurrentLinkedQueue<>();

    initValuesFromConfig();
    initCollections();
  }

  private void initValuesFromConfig() {
    tcpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT, 55555);
    udpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT, 55556);
    serverName = cfg.getProperty(Config.SERVER_DEFAULT_SERVER_NAME, "Tanks Game Server");
    matchDuration = cfg.getPropertyInt(Config.SERVER_MATCH_DURATION, 15);
    roundDuration = cfg.getPropertyInt(Config.SERVER_ROUND_DURATION, 2);
  }

  private void initCollections() {
    players = new ArrayList<>();
    playerById = new HashMap<>();
    leavingPlayerIds = new ConcurrentLinkedQueue<>();
    incomingPlayers = new ConcurrentLinkedQueue<>();
    incomingPlayerSnapshots = new ConcurrentLinkedQueue<>();

    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();

    incomingMessages = new ConcurrentLinkedQueue<>();
    outgoingMessages = new ConcurrentLinkedQueue<>();

    incomingCommands = new ConcurrentLinkedQueue<>();
    outgoingCommands = new ConcurrentLinkedQueue<>();

    playerStats = new HashMap<>();
    chatHistory = new ArrayList<>();
  }

  /**
   * Used when starting new Match
   */
  public void reinitialize() {
    initCollections();
    matchStartTime = 0;
    matchEndTime = 0;
    roundEndTime = 0;
    // clear all data objects
    // clear current player list
    // handshake all players again

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

  public int getMatchDuration() {
    return matchDuration;
  }

  public int getRoundDuration() {
    return roundDuration;
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
