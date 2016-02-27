package org.game.tanks.database.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnauthorizedActionHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date time;
  private String ipAddress;
  private String serializedObject;

  public Long getId() {
    return id;
  }

  public UnauthorizedActionHistory setId(Long id) {
    this.id = id;
    return this;
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
