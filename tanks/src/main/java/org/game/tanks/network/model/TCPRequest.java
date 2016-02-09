package org.game.tanks.network.model;

import java.io.Serializable;

public class TCPRequest implements Serializable {

  private static final long serialVersionUID = 24998497837730612L;
  private PacketType type = PacketType.UNDEFINED;
  private byte[] bytes;

  public TCPRequest() {
  }

  public TCPRequest(PacketType type, byte[] bytes) {
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
