package org.game.tanks.cfg;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.game.tanks.core.Game;
import org.game.tanks.server.core.Server;
import org.game.tanks.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Autowired
  FileUtils fileUtils;

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

}
