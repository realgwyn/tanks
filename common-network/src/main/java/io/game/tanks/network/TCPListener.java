package io.game.tanks.network;

import io.game.tanks.network.model.TCPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface TCPListener {

  void receivedTCPMessage(Connection conn, TCPMessage message);

}
