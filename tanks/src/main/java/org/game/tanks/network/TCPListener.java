package org.game.tanks.network;

import org.game.tanks.network.model.TCPRequest;
import org.game.tanks.network.model.TCPResponse;

import com.esotericsoftware.kryonet.Connection;

public interface TCPListener {

  void receivedRequest(Connection conn, TCPRequest request);
  
  void receivedResponse(Connection conn, TCPResponse request);
  
}
