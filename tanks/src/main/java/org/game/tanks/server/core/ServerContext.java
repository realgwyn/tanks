package org.game.tanks.server.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.model.MapModel;
import org.game.tanks.network.model.AdminCommand;
import org.game.tanks.network.model.Command;
import org.game.tanks.network.model.CommunicationMessage;
import org.game.tanks.network.model.GameEvent;
import org.game.tanks.network.model.Handshake;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.gameplay.GameType;
import org.game.tanks.server.gameplay.TeamDeathmatch;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contains thread-safe collections
 * 
 * @author rafal.kojta
 *
 */
@Component
public class ServerContext {

  @Autowired
  Config cfg;

  private Queue<PlayerServerModel> syncPlayersQueue;

  private Queue<Handshake> incomingHandshakes;
  private Queue<PlayerServerModel> incomingPlayers;
  private Queue<Integer> leavingPlayerIds;

  private Queue<PlayerSnapshot> incomingPlayerSnapshots;

  private Queue<GameEvent> incomingGameEvents;
  private Queue<GameEvent> outgoingGameEvents;

  private Queue<Command> incomingCommands;
  private Queue<Command> outgoingCommands;

  private Queue<CommunicationMessage> incomingMessages;
  private Queue<CommunicationMessage> outgoingMessages;

  private Queue<AdminCommand> incomingAdminCommands;

  private Queue<ChatMessage> chatHistory;
  // private Queue<Task> pendingTasks;

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

  @Autowired
  private TeamDeathmatch defaultGameType;
  private GameType gameType;

  /**
   * Used when starting the Server
   */
  @PostConstruct
  public void init() {
    incomingHandshakes = new ConcurrentLinkedQueue<>();
    incomingAdminCommands = new ConcurrentLinkedQueue<>();

    initValuesFromConfig();
    initCollections();
  }

  private void initValuesFromConfig() {
    tcpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT, 55555);
    udpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT, 55556);
    serverName = cfg.getProperty(Config.SERVER_DEFAULT_SERVER_NAME, "Tanks Game Server");
    matchDuration = cfg.getPropertyInt(Config.SERVER_MATCH_DURATION, 1);
    roundDuration = cfg.getPropertyInt(Config.SERVER_ROUND_DURATION, 1);
    gameType = defaultGameType;
  }

  private void initCollections() {
    leavingPlayerIds = new ConcurrentLinkedQueue<>();
    incomingPlayers = new ConcurrentLinkedQueue<>();

    incomingPlayerSnapshots = new ConcurrentLinkedQueue<>();

    incomingGameEvents = new ConcurrentLinkedQueue<>();
    outgoingGameEvents = new ConcurrentLinkedQueue<>();

    incomingMessages = new ConcurrentLinkedQueue<>();
    outgoingMessages = new ConcurrentLinkedQueue<>();

    incomingCommands = new ConcurrentLinkedQueue<>();
    outgoingCommands = new ConcurrentLinkedQueue<>();

    chatHistory = new ConcurrentLinkedQueue<>();
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

  public Queue<ChatMessage> getChatHistory() {
    return chatHistory;
  }

  public int getMatchDurationMinutes() {
    return matchDuration;
  }

  public int getRoundDurationMinutes() {
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

  public GameType getGameType() {
    return gameType;
  }

  public ServerContext setRoundEndTime(long roundEndTime) {
    this.roundEndTime = roundEndTime;
    return this;
  }
  //
  // public PlayerServerModel getPlayerById(long id) {
  // return playerById.get(id);
  // }
  // public List<PlayerServerModel> getPlayers() {
  // return players;
  // }
  //
  // public Map<Long, PlayerInfo> getPlayerStatsAll() {
  // return playerStats;
  // }
}
