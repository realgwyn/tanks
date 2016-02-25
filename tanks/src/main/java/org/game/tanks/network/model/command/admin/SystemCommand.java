package org.game.tanks.network.model.command.admin;

import org.game.tanks.network.model.AdminCommand;

public class SystemCommand extends AdminCommand {

  private static final long serialVersionUID = 386997896742861838L;

  public enum SystemCommandName {
    ENABLE_PROCESS_SCHEDULER, ENABLE_ANTI_CHEAT, ENABLE_PLAYER_POSITION_CORRECTION
  }

  private SystemCommandName name;
  private String value;

  public SystemCommandName getName() {
    return name;
  }

  public SystemCommand setName(SystemCommandName name) {
    this.name = name;
    return this;
  }

  public String getValue() {
    return value;
  }

  public SystemCommand setValue(String value) {
    this.value = value;
    return this;
  }

}
