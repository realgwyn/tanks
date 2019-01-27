package io.game.tanks.network;

public class ConnectionAddress {

  private String name;
  private String address;
  private int udpPort;
  private int tcpPort;

  public ConnectionAddress() {
  }

  public ConnectionAddress(String name, String address, int udpPort, int tcpPort) {
    this.name = name;
    this.address = address;
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

  public String getAddress() {
    return address;
  }

  public ConnectionAddress setAddress(String ip) {
    this.address = ip;
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

  @Override
  public String toString() {
    return "ConnectionAddress [name=" + name + ", address=" + address + ", udpPort=" + udpPort + ", tcpPort=" + tcpPort + "]";
  }

}
