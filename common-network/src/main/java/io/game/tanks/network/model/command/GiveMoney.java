package io.game.tanks.network.model.command;

import io.game.tanks.network.model.Command;

public class GiveMoney extends Command {

  private static final long serialVersionUID = 4657307547233722583L;
  private int value;

  public GiveMoney() {

  }

  public int getValue() {
    return value;
  }

  public GiveMoney setValue(int value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return "GiveMoney [value=" + value + "]";
  }

}
