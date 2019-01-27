package io.tanks.common.network.model.command;

import io.tanks.common.network.state.ClientStateType;
import io.tanks.common.network.model.Command;

public class ChangeState extends Command {

  private static final long serialVersionUID = -8464279087332407343L;
  private ClientStateType type;
  private long changeStateTime;

  public ChangeState() {

  }

  public ClientStateType getType() {
    return type;
  }

  public ChangeState setType(ClientStateType type) {
    this.type = type;
    return this;
  }

  public long getChangeStateTime() {
    return changeStateTime;
  }

  public ChangeState setChangeStateTime(long changeStateTime) {
    this.changeStateTime = changeStateTime;
    return this;
  }

  @Override
  public String toString() {
    return "ChangeState [type=" + type + ", changeStateTime=" + changeStateTime + "]";
  }

}
