package io.tanks.common.network;

import io.tanks.common.network.model.UDPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface UDPListener {

  void receivedUDPMessage(Connection conn, UDPMessage message);

}
