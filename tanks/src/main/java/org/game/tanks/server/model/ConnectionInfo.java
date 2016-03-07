package org.game.tanks.server.model;

public class ConnectionInfo {

  private int connectionId;
  private String networkAddress;

  public int getConnectionId() {
    return connectionId;
  }

  public ConnectionInfo setConnectionId(int connectionId) {
    this.connectionId = connectionId;
    return this;
  }

  public String getNetworkAddress() {
    return networkAddress;
  }

  public ConnectionInfo setNetworkAddress(String networkAddress) {
    this.networkAddress = networkAddress;
    return this;
  }

}