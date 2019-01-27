package io.tanks.common.network;

import io.tanks.common.network.model.TCPMessage;

import com.esotericsoftware.kryonet.Connection;

public interface TCPListener {

  void receivedTCPMessage(Connection conn, TCPMessage message);

}
