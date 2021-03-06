package io.tanks.common.network.model.command;

import io.tanks.common.network.model.Command;

public class GiveItem extends Command {

  private static final long serialVersionUID = -8993601325113332705L;

  public enum ItemType {
    AMMO, HEALTH, ARMOR
  }

  private ItemType type;
  private int value;

  public GiveItem() {

  }

  public ItemType getType() {
    return type;
  }

  public GiveItem setType(ItemType type) {
    this.type = type;
    return this;
  }

  public int getValue() {
    return value;
  }

  public GiveItem setValue(int value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return "GiveItem [type=" + type + ", value=" + value + "]";
  }

}
