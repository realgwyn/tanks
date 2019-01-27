package io.tanks.common.network.model.command.admin;

import io.tanks.common.network.model.AdminCommand;

public class ChangeMap extends AdminCommand {

  private static final long serialVersionUID = 5548555987457829502L;
  private String mapName;

  public ChangeMap() {

  }

  public String getMapName() {
    return mapName;
  }

  public ChangeMap setMapName(String mapName) {
    this.mapName = mapName;
    return this;
  }

}
