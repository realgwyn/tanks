package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;
import org.game.tanks.state.State.StateType;

public class ChangeState extends Command {

  private static final long serialVersionUID = -8464279087332407343L;
  private StateType type;

  public StateType getType() {
    return type;
  }

  public void setType(StateType type) {
    this.type = type;
  }

}
