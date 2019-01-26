package org.game.tanks.cfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.game.tanks.client.core.Game;
import org.game.tanks.server.core.Server;
import org.game.tanks.utils.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO: saving config values back to config.properties file when exiting the game
// TODO: restoring config.properties values from default_config.properties - just copy file content and load
// fileUtils.readPropertiesFromResource("config.properties");
@Configuration
@PropertySource(value = { "classpath:application.properties" })
@EnableTransactionManagement
public class Config {

  public final static String MAP_NAMES = "map.names";
  public final static String GAME_NAME = "game.name";
  public final static String GAME_UPDATE_RATE = "game.updaterate";
  public final static String GAME_FULLSCREEN = "game.fullscreen";
  public final static String GAME_RESOLUTION_WIDTH = "game.resolution.width";
  public final static String GAME_RESOLUTION_HEIGHT = "game.resolution.height";
  public final static String GAME_RESOLUTION_SCALE = "game.resolution.scale";
  public final static String GAME_ENABLE_NETWORK_DEBUG_MODE = "game.enableNetworkDebugMode";
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
  public final static String SERVER_MAX_CHAT_HISTORY_SIZE = "server.maxChatHistorySize";
  public final static String SERVER_ROUND_DURATION_SECONDS = "server.roundDurationSeconds";
  public final static String SERVER_MATCH_DURATION_SECONDS = "server.matchDurationSeconds";
  public final static String SERVER_ROUND_START_FREEZE_TIME_SECONDS = "server.roundStartFreezeTimeDurationSeconds";
  public final static String SERVER_ENABLE_FRIENDLY_FIRE = "server.enableFriendlyfire";
  public final static String SERVER_OFFLINE_DEBUG_MODE = "server.enableOfflineDebugMode";
  public final static String SERVER_ENABLE_NETWORK_DEBUG_MODE = "server.enableNetworkDebugMode";

  @Autowired
  private FileUtils fileUtils;

  @Autowired
  private Environment environment;

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

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(new String[] { "org.game.tanks.database.domain" });
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
    return dataSource;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    return properties;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory s) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(s);
    return txManager;
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

  public int getPropertyInt(String propertyName, int defaultValue) {
    String property = configProperties.getProperty(propertyName);
    return property != null ? Integer.parseInt(property) : defaultValue;
  }

  public String getProperty(String propertyName) {
    return configProperties.getProperty(propertyName);
  }

  public String getProperty(String propertyName, String defaultValue) {
    String property = configProperties.getProperty(propertyName);
    return property != null ? property : defaultValue;
  }

  public double getPropertyDouble(String propertyName) {
    return Double.parseDouble(configProperties.getProperty(propertyName));
  }

  public double getPropertyDouble(String propertyName, double defaultValue) {
    String property = configProperties.getProperty(propertyName);
    return property != null ? Double.parseDouble(property) : defaultValue;
  }

  public boolean getPropertyBoolean(String propertyName) {
    return Boolean.parseBoolean(configProperties.getProperty(propertyName));
  }

  public boolean getPropertyBoolean(String propertyName, boolean defaultValue) {
    String property = configProperties.getProperty(propertyName);
    return property != null ? Boolean.parseBoolean(property) : defaultValue;
  }

  public List<String> getPropertyListString(String propertyName) {
    String listString = configProperties.getProperty(propertyName);
    listString = StringUtils.strip(listString, LIST_DELIMITER);
    return new ArrayList<>(Arrays.asList(listString.split(LIST_DELIMITER)));
  }

  public List<String> getPropertyListString(String propertyName, List<String> defaultValues) {
    String listString = configProperties.getProperty(propertyName);
    if (listString != null) {
      listString = StringUtils.strip(listString, LIST_DELIMITER);
      return new ArrayList<>(Arrays.asList(listString.split(LIST_DELIMITER)));
    } else {
      return defaultValues;
    }
  }

}
