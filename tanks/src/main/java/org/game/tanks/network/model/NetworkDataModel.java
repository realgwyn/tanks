package org.game.tanks.network.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkDataModel {

  public static void register(EndPoint endpoint) {
    Kryo kryo = endpoint.getKryo();
    kryo.register(PacketType.class);
    kryo.register(byte[].class);
    kryo.register(TCPRequest.class);
    kryo.register(TCPResponse.class);
    kryo.register(UDPRequest.class);
    kryo.register(UDPResponse.class);
  }

}
