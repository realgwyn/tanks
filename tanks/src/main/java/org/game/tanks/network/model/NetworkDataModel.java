package org.game.tanks.network.model;

import org.game.tanks.network.model.command.ChangeName;
import org.game.tanks.network.model.command.ChangeState;
import org.game.tanks.network.model.command.Connect;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.Ping;
import org.game.tanks.network.model.command.PlayerStats;
import org.game.tanks.network.model.command.PlayerStatsAll;
import org.game.tanks.network.model.command.PlayersLatencyAll;
import org.game.tanks.network.model.command.TimeEvent;
import org.game.tanks.network.model.event.HitEvent;
import org.game.tanks.network.model.event.KillEvent;
import org.game.tanks.network.model.event.RespawnEvent;
import org.game.tanks.network.model.event.ShootEvent;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.message.ServerMessage;
import org.game.tanks.network.model.udp.GameSnapshot;
import org.game.tanks.network.model.udp.PlayerPosition;
import org.game.tanks.network.model.udp.PlayerSnapshot;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkDataModel {

  public static void register(EndPoint endpoint) {
    Kryo kryo = endpoint.getKryo();
    kryo.register(TCPMessage.class);
    kryo.register(Command.class);
    kryo.register(GameEvent.class);
    kryo.register(ChatMessage.class);
    kryo.register(ServerMessage.class);
    kryo.register(ShootEvent.class);
    kryo.register(RespawnEvent.class);
    kryo.register(KillEvent.class);
    kryo.register(HitEvent.class);
    kryo.register(ChangeName.class);
    kryo.register(ChangeState.class);
    kryo.register(Connect.class);
    kryo.register(Disconnect.class);
    kryo.register(Ping.class);
    kryo.register(PlayersLatencyAll.class);
    kryo.register(PlayerStats.class);
    kryo.register(PlayerStatsAll.class);
    kryo.register(TimeEvent.class);

    kryo.register(UDPMessage.class);
    kryo.register(GameSnapshot.class);
    kryo.register(PlayerSnapshot.class);
    kryo.register(PlayerPosition.class);
  }

}
