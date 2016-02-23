package org.game.tanks.network;

import org.game.tanks.network.model.UDPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface UDPListener {

  void receivedUDPMessage(Connection conn, UDPMessage message);

}
