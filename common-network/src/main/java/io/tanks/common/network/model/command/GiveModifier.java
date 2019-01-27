package io.tanks.common.network.model.command;

import io.tanks.common.network.model.Command;

/**
 * @author rafal.kojta
 */
public class GiveModifier extends Command {

  private static final long serialVersionUID = 5687839991769109196L;

  public enum ModifierType {
    VELOCITY, ACCELERATION, FRICTION
  }

  private int playerId;
  private ModifierType type;
  private double value;

  public GiveModifier() {

  }

  public int getPlayerId() {
    return playerId;
  }

  public GiveModifier setPlayerId(int playerId) {
    this.playerId = playerId;
    return this;
  }

  public ModifierType getType() {
    return type;
  }

  public GiveModifier setType(ModifierType type) {
    this.type = type;
    return this;
  }

  public double getValue() {
    return value;
  }

  public GiveModifier setValue(double value) {
    this.value = value;
    return this;
  }

  @Override
  public String toString() {
    return "GiveModifier [playerId=" + playerId + ", type=" + type + ", value=" + value + "]";
  }

}
