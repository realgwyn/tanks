package org.game.tanks.network;

import com.esotericsoftware.kryonet.Connection;

public interface ConnectionListener {

  public void connected(Connection c);

  public void disconnected(Connection c);

}
