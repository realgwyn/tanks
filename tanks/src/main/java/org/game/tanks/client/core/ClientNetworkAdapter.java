package org.game.tanks.client.core;

import org.game.tanks.network.NetworkAdapter;
import org.game.tanks.network.model.TCPMessage;
import org.game.tanks.network.model.UDPMessage;
import org.springframework.stereotype.Component;

import com.esotericsoftware.kryonet.Connection;

@Component
public class ClientNetworkAdapter extends NetworkAdapter {

  @Override
  public void receivedUDPMessage(Connection conn, UDPMessage message) {
    // TODO Auto-generated method stub

  }

  @Override
  public void receivedTCPMessage(Connection conn, TCPMessage message) {
    // TODO Auto-generated method stub

  }

}
