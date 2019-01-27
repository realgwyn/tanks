package io.tanks.common.network.model.udp;

import java.io.Serializable;

public class PlayerPosition implements Serializable {

  private static final long serialVersionUID = 7085286834653399272L;
  public int playerId;
  public int x;
  public int y;
  public float bodyAngle;
  public float towerAngle;

  public PlayerPosition() {

  }
}
