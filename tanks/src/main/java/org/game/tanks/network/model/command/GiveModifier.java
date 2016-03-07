package org.game.tanks.network.model.command;

import org.game.tanks.network.model.Command;

/**
 * @author rafal.kojta
 */
public class GiveModifier extends Command {

  public enum ModifierType {
    VELOCITY, ACCELERATION, FRICTION
  }

  private int playerId;
  private ModifierType type;
  private double value;

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
