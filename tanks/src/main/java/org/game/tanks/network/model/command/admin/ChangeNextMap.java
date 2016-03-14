package org.game.tanks.network.model.command.admin;

import org.game.tanks.network.model.AdminCommand;

public class ChangeNextMap extends AdminCommand {

  private static final long serialVersionUID = -6532318002778838074L;
  private String mapName;

  public ChangeNextMap() {

  }

  public String getMapName() {
    return mapName;
  }

  public ChangeNextMap setMapName(String mapName) {
    this.mapName = mapName;
    return this;
  }

}
