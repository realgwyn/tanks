package org.game.tanks.cfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.game.tanks.core.Game;
import org.game.tanks.server.core.Server;
import org.game.tanks.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Autowired
  private FileUtils fileUtils;

  private final String LIST_DELIMITER = ";";

  private Properties configProperties;

  @Bean
  public Game gameRunner() {
    return new Game();
  }

  @Bean
  public Server serverRunner() {
    return new Server();
  }

  @PostConstruct
  public void init() {
    configProperties = fileUtils.readPropertiesFromResource("config.properties");
  }

  public Properties getConfigProperties() {
    return configProperties;
  }

  public int getPropertyInt(String propertyName) {
    return Integer.parseInt(configProperties.getProperty(propertyName));
  }

  public String getProperty(String propertyName) {
    return configProperties.getProperty(propertyName);
  }

  public double getPropertyDouble(String propertyName) {
    return Double.parseDouble(configProperties.getProperty(propertyName));
  }

  public boolean getPropertyBoolean(String propertyName) {
    return Boolean.parseBoolean(configProperties.getProperty(propertyName));
  }

  public List<String> getPropertyListString(String propertyName) {
    String listString = configProperties.getProperty(propertyName);
    listString = StringUtils.strip(listString, LIST_DELIMITER);
    return new ArrayList<>(Arrays.asList(listString.split(LIST_DELIMITER)));
  }

  public final static String MAP_NAMES = "map.names";
  public final static String GAME_NAME = "game.name";
  public final static String GAME_UPDATE_RATE = "game.updaterate";
  public final static String GAME_FULLSCREEN = "game.fullscreen";
  public final static String GAME_RESOLUTION_WIDTH = "game.resolution.width";
  public final static String GAME_RESOLUTION_HEIGHT = "game.resolution.height";
  public final static String GAME_RESOLUTION_SCALE = "game.resolution.scale";
  public final static String SERVER_MAX_PLAYERS = "server.maxPlayers";
  public final static String SERVER_MAX_PLAYERS_PER_TEAM = "server.maxPlayersPerTeam";
  public final static String SERVER_DEFAULT_TCP_PORT = "server.defaultTcpPort";
  public final static String SERVER_DEFAULT_UDP_PORT = "server.defaultUdpPort";
  public final static String SERVER_DEFAULT_SERVER_NAME = "server.defaultServerName";
  public final static String SERVER_ENABLE_PROCESS_SCHEDULER = "server.enableProcessScheduler";
  public final static String SERVER_ENABLE_ANTI_CHEAT = "server.enableAntiCheat";
  public final static String SERVER_ENABLE_PLAYER_POSITION_CORRECTION = "server.enablePlayerPositionCorrection";
  public final static String SERVER_ENABLE_PACKET_VALIDATION = "server.enablePacketValidation";
  public final static String SERVER_UPDATE_RATE = "server.updaterate";
  public final static String SERVER_NETWORK_PING_SENDING_FREQUENCY = "server.network.pingSendingFrequency";
  public final static String SERVER_NETWORK_STATS_SENDING_FREQUENCY = "server.network.statsSendingFrequency";

}
