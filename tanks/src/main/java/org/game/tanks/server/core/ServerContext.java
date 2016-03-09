package org.game.tanks.server.core;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.model.MapModel;
import org.game.tanks.server.gameplay.GameType;
import org.game.tanks.server.gameplay.TeamDeathmatch;
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

  private int tcpPort;
  private int udpPort;
  private String serverName;

  private MapModel currentMap;
  private MapModel nextMap;

  // This flag is used to ignore all old/late packets that are incoming
  // into the server from the old game round (lagging shoot events, move events etc)
  private boolean newRoundFlipFlag;
  private int matchDurationSeconds;
  private int roundDurationSeconds;
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
    tcpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT, 55555);
    udpPort = cfg.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT, 55556);
    serverName = cfg.getProperty(Config.SERVER_DEFAULT_SERVER_NAME, "Tanks Game Server");
    matchDurationSeconds = cfg.getPropertyInt(Config.SERVER_MATCH_DURATION_SECONDS, 15);
    roundDurationSeconds = cfg.getPropertyInt(Config.SERVER_ROUND_DURATION_SECONDS, 5);
    gameType = defaultGameType;
  }

  /**
   * Used when starting new Match
   */
  public void reinitialize() {
    matchStartTime = 0;
    matchEndTime = 0;
    roundEndTime = 0;
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

  public int getMatchDurationSeconds() {
    return matchDurationSeconds;
  }

  public int getMatchDurationMillis() {
    return matchDurationSeconds * 1000;
  }

  public int getRoundDurationSeconds() {
    return roundDurationSeconds;
  }

  public int getRoundDuationMillis() {
    return roundDurationSeconds * 1000;
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

  public boolean getNewRoundFlipFlag() {
    return newRoundFlipFlag;
  }

  public void setNewRoundFlipFlag(boolean newRoundFlipFlag) {
    this.newRoundFlipFlag = newRoundFlipFlag;
  }

}
