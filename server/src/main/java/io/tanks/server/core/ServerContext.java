package io.tanks.server.core;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.server.cfg.ServerConfig;
import io.tanks.server.gameplay.DevelopmentGameType;
import io.tanks.server.gameplay.GameType;

/**
 * @author rafal.kojta
 *
 */
@Component
public class ServerContext {

  private final static Logger logger = Logger.getLogger(ServerContext.class);

  @Autowired
  ServerConfig cfg;

  private int tcpPort;
  private int udpPort;
  private String serverName;

  // This flag is used to ignore all old/late packets that are incoming
  // into the server from the old game round (lagging shoot events, move events etc)
  private boolean newRoundFlipFlag;
  private int matchDurationSeconds;
  private int roundDurationSeconds;
  private long matchStartTime;
  private long matchEndTime;
  private long roundEndTime;

  @Autowired
  // private TeamDeathmatch defaultGameType;
  private DevelopmentGameType defaultGameType;

  private GameType gameType;

  /**
   * Used when starting the Server
   */
  @PostConstruct
  public void init() {
    tcpPort = cfg.getDefaultTcpPort();
    udpPort = cfg.getDefaultUdpPort();
    serverName = cfg.getDefaultServerName();
    matchDurationSeconds = cfg.getMatchDurationSeconds();
    roundDurationSeconds = cfg.getRoundDurationSeconds();
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
    logger.debug("Setting Match Start Time at: " + matchStartTime);
    this.matchStartTime = matchStartTime;
    return this;
  }

  public long getMatchEndTime() {
    return matchEndTime;
  }

  public ServerContext setMatchEndTime(long matchEndTime) {
    logger.debug("Setting Match End Time at: " + matchEndTime);
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
    logger.debug("Setting Round End Time at: " + matchEndTime);
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
