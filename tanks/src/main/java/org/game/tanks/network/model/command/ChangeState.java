package org.game.tanks.network.model.command;

import org.game.tanks.client.state.ClientState.ClientStateType;
import org.game.tanks.network.model.Command;

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
