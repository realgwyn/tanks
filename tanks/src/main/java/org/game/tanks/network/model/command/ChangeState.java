package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;
import org.game.tanks.state.ClientState.ClientStateType;

public class ChangeState extends Command {

  private static final long serialVersionUID = -8464279087332407343L;
  private ClientStateType type;

  public ClientStateType getType() {
    return type;
  }

  public void setType(ClientStateType type) {
    this.type = type;
  }

}
