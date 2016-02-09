package org.game.tanks.network.model;

import java.io.Serializable;

public class UDPRequest implements Serializable {

  private static final long serialVersionUID = -7487759972994784725L;
  private PacketType type = PacketType.UNDEFINED;
  private byte[] bytes;

  public UDPRequest() {
  }

  public UDPRequest(PacketType type, byte[] bytes) {
    this.type = type;
    this.bytes = bytes;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public PacketType getType() {
    return type;
  }

  public void setType(PacketType type) {
    this.type = type;
  }

}
