package org.game.tanks.network.model;

import java.io.Serializable;

public class TCPResponse implements Serializable {

  private static final long serialVersionUID = -801144613459762698L;
  private PacketType type = PacketType.UNDEFINED;
  private byte[] bytes;

  public TCPResponse() {
  }

  public TCPResponse(PacketType type, byte[] bytes) {
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
