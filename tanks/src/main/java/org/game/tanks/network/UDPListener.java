package org.game.tanks.network;

import org.game.tanks.network.model.UDPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface UDPListener {

  void receivedRequest(Connection conn, UDPMessage request);

  void receivedResponse(Connection conn, UDPMessage response);

}
