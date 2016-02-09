package org.game.tanks.network.model;

import java.io.Serializable;

public enum PacketType implements Serializable{

  MESSAGE, SNAPSHOT, COMMAND, UNDEFINED
}
