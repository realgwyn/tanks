package org.game.tanks.server.core;

import org.springframework.stereotype.Component;

@Component
public class ServerConfig {

  private int maxPlayers;
  private int maxPlayersPerTeam;
  private String defaultNetworkAddress;
  private int defaultTcpPort;
  private int defaultUdpPort;

}
