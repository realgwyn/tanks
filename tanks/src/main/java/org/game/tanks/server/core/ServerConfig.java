package org.game.tanks.server.core;

import org.springframework.stereotype.Component;

@Component
public class ServerConfig {

  private int maxPlayers;
  private int maxPlayersPerTeam;
  private int defaultTcpPort;
  private int defaultUdpPort;
  private String networkAddress;
  private int tcpPort;
  private int udpPort;

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }

  public int getMaxPlayersPerTeam() {
    return maxPlayersPerTeam;
  }

  public void setMaxPlayersPerTeam(int maxPlayersPerTeam) {
    this.maxPlayersPerTeam = maxPlayersPerTeam;
  }

  public int getDefaultTcpPort() {
    return defaultTcpPort;
  }

  public void setDefaultTcpPort(int defaultTcpPort) {
    this.defaultTcpPort = defaultTcpPort;
  }

  public int getDefaultUdpPort() {
    return defaultUdpPort;
  }

  public void setDefaultUdpPort(int defaultUdpPort) {
    this.defaultUdpPort = defaultUdpPort;
  }

  public String getNetworkAddress() {
    return networkAddress;
  }

  public void setNetworkAddress(String networkAddress) {
    this.networkAddress = networkAddress;
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

}
