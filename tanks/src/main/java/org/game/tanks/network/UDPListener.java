package org.game.tanks.network;

import org.game.tanks.network.model.UDPRequest;
import org.game.tanks.network.model.UDPResponse;

import com.esotericsoftware.kryonet.Connection;

public interface UDPListener {

  void receivedRequest(Connection conn, UDPRequest request);
  
  void receivedResponse(Connection conn, UDPResponse response);

}
