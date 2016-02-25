package org.game.tanks.database;

public class MalformedPacketHistory {

  Long time;
  String ipAddress;
  String serializedObject;

  public Long getTime() {
    return time;
  }

  public MalformedPacketHistory setTime(Long time) {
    this.time = time;
    return this;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public MalformedPacketHistory setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  public String getSerializedObject() {
    return serializedObject;
  }

  public MalformedPacketHistory setSerializedObject(String serializedObject) {
    this.serializedObject = serializedObject;
    return this;
  }

}
