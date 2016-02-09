package org.game.tanks.network;

public class ConnectionAddress {
  
  private String name;
  private String ip;
  private int udpPort;
  private int tcpPort;
  
  public ConnectionAddress(){}
  
  public ConnectionAddress(String name, String ip, int udpPort, int tcpPort){
    this.name = name;
    this.ip = ip;
    this.udpPort = udpPort;
    this.tcpPort = tcpPort;
  }
  
  public String getName() {
    return name;
  }
  public ConnectionAddress setName(String name) {
    this.name = name;
    return this;
  }
  public String getIp() {
    return ip;
  }
  public ConnectionAddress setIp(String ip) {
    this.ip = ip;
    return this;
  }
  public int getUdpPort() {
    return udpPort;
  }
  public ConnectionAddress setUdpPort(int udpPort) {
    this.udpPort = udpPort;
    return this;
  }
  public int getTcpPort() {
    return tcpPort;
  }
  public ConnectionAddress setTcpPort(int tcpPort) {
    this.tcpPort = tcpPort;
    return this;
  }

}
