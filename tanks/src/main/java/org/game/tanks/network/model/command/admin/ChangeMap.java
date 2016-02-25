package org.game.tanks.network.model.command.admin;

import org.game.tanks.network.model.AdminCommand;

public class ChangeMap extends AdminCommand {

  private static final long serialVersionUID = 5548555987457829502L;
  private String mapName;

  public String getMapName() {
    return mapName;
  }

  public ChangeMap setMapName(String mapName) {
    this.mapName = mapName;
    return this;
  }


}
