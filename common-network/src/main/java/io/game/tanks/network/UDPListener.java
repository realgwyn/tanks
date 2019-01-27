package io.game.tanks.network;

import io.game.tanks.network.model.UDPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface UDPListener {

  void receivedUDPMessage(Connection conn, UDPMessage message);

}
