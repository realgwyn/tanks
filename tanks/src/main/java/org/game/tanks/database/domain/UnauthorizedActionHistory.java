package org.game.tanks.database.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class UnauthorizedActionHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date time;

  private String ipAddress;
  @NotNull
  private String serializedObject;

  public Long getId() {
    return id;
  }

  public Date getTime() {
    return time;
  }

  public UnauthorizedActionHistory setTime(Date time) {
    this.time = time;
    return this;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public UnauthorizedActionHistory setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  public String getSerializedObject() {
    return serializedObject;
  }

  public UnauthorizedActionHistory setSerializedObject(String serializedObject) {
    this.serializedObject = serializedObject;
    return this;
  }

}
