package io.tanks.server.database.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class MalformedPacketHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  Date time;

  String ipAddress;
  @NotNull
  String serializedObject;

  @Transient
  Object object;

  public Date getTime() {
    return time;
  }

  public MalformedPacketHistory setTime(Date time) {
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

  public Object getObject() {
    return object;
  }

  public MalformedPacketHistory setObject(Object object) {
    this.object = object;
    return this;
  }

}
