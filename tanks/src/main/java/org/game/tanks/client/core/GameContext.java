package org.game.tanks.client.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.client.model.PlayerGameModel;
import org.game.tanks.model.MapModel;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameContext {

  @Autowired
  Config cfg;

  private long serverTimeOffset;// XXX: is this really needed anywhere?
  private PlayerGameModel player;

  private String serverAddress;
  private int serverUdpPort;
  private int serverTcpPort;

  private List<PlayerGameModel> players;
  private Map<Long, PlayerGameModel> playerById;
  private Queue<TCPMessage> incommingMessages;
  private Queue<TCPMessage> defferedMessages;
  private List<ChatMessage> chatHistory;

  private long matchStartTime;
  private long matchEndTime;
  private long roundEndTime;

  private MapModel currentMap;
  private MapModel nextMap;

  @PostConstruct
  public void init() {
    initValuesFromConfig();
    initCollections();
  }

  public void reinitialize() {
    initCollections();
    player = new PlayerGameModel();
    // set player name from options
  }

  private void initCollections() {
    players = new ArrayList<>();
    playerById = new HashMap<>();
    incommingMessages = new ConcurrentLinkedQueue<>();
    defferedMessages = new ConcurrentLinkedQueue<>();
    chatHistory = new ArrayList<>();
  }

  private void initValuesFromConfig() {
    // TODO
  }

  public int getPlayerPosX() {
    return player.getModel().x;
  }

  public void setPlayerPosX(int playerPosX) {
    this.player.getModel().x = playerPosX;
  }

  public int getPlayerPosY() {
    return player.getModel().y;
  }

  public void setPlayerPosY(int playerPosY) {
    this.player.getModel().y = playerPosY;
  }

  public long getPlayerId() {
    return player.getPlayerID();
  }

  public void setPlayerId(long playerId) {
    this.player.setPlayerID(playerId);
  }

  public String getPlayerName() {
    return player.getPlayerName();
  }

  public void setPlayerName(String playerName) {
    this.player.setPlayerName(playerName);
  }

  public long getServerTimeOffset() {
    return serverTimeOffset;
  }

  public void setServerTimeOffset(long serverTimeOffset) {
    this.serverTimeOffset = serverTimeOffset;
  }

  public String getServerAddress() {
    return serverAddress;
  }

  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

  public int getServerUdpPort() {
    return serverUdpPort;
  }

  public void setServerUdpPort(int serverUdpPort) {
    this.serverUdpPort = serverUdpPort;
  }

  public int getServerTcpPort() {
    return serverTcpPort;
  }

  public void setServerTcpPort(int serverTcpPort) {
    this.serverTcpPort = serverTcpPort;
  }

  public Queue<TCPMessage> getIncommingMessages() {
    return incommingMessages;
  }

  public Queue<TCPMessage> getDefferedMessages() {
    return defferedMessages;
  }

  public List<PlayerGameModel> getPlayers() {
    return players;
  }

  public List<ChatMessage> getChatHistory() {
    return chatHistory;
  }

  public void addPlayer(PlayerGameModel player) {
    players.add(player);
    playerById.put(player.getPlayerID(), player);
  }

  public Map<Long, PlayerGameModel> getPlayerById() {
    return playerById;
  }

  public long getMatchStartTime() {
    return matchStartTime;
  }

  public void setMatchStartTime(long matchStartTime) {
    this.matchStartTime = matchStartTime;
  }

  public long getMatchEndTime() {
    return matchEndTime;
  }

  public void setMatchEndTime(long matchEndTime) {
    this.matchEndTime = matchEndTime;
  }

  public long getRoundEndTime() {
    return roundEndTime;
  }

  public void setRoundEndTime(long roundEndTime) {
    this.roundEndTime = roundEndTime;
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

  public PlayerGameModel getPlayer() {
    return player;
  }

}
