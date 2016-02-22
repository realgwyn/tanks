package org.game.tanks.network;

import org.game.tanks.network.model.TCPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface TCPListener {

  void receivedTCPMessage(Connection conn, TCPMessage request);

}
