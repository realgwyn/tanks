package org.game.tanks.network.model;

import java.awt.geom.Point2D;

import org.game.tanks.network.model.command.ChangeName;
import org.game.tanks.network.model.command.ChangeState;
import org.game.tanks.network.model.command.Connect;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.Ping;
import org.game.tanks.network.model.command.PlayerStats;
import org.game.tanks.network.model.command.PlayerStatsAll;
import org.game.tanks.network.model.command.Pong;
import org.game.tanks.network.model.command.TimeEvent;
import org.game.tanks.network.model.command.admin.BanPlayer;
import org.game.tanks.network.model.command.admin.BanPlayer.BanReason;
import org.game.tanks.network.model.command.admin.ChangeMap;
import org.game.tanks.network.model.command.admin.ChangeNextMap;
import org.game.tanks.network.model.command.admin.KickPlayer;
import org.game.tanks.network.model.command.admin.RestartMap;
import org.game.tanks.network.model.command.admin.SystemCommand;
import org.game.tanks.network.model.command.admin.SystemCommand.SystemCommandName;
import org.game.tanks.network.model.event.HitEvent;
import org.game.tanks.network.model.event.KillEvent;
import org.game.tanks.network.model.event.MoveEvent;
import org.game.tanks.network.model.event.RespawnEvent;
import org.game.tanks.network.model.event.ShootEvent;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.network.model.message.ServerMessage;
import org.game.tanks.network.model.udp.GameSnapshot;
import org.game.tanks.network.model.udp.PlayerPosition;
import org.game.tanks.network.model.udp.PlayerSnapshot;
import org.game.tanks.server.state.ServerState.ServerStateType;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkDataModel {

  public static void register(EndPoint endpoint) {
    Kryo kryo = endpoint.getKryo();
    kryo.register(TCPMessage.class);

    kryo.register(Command.class);
    kryo.register(ChangeName.class);
    kryo.register(ChangeState.class);
    kryo.register(Connect.class);
    kryo.register(Disconnect.class);
    kryo.register(Ping.class);
    kryo.register(Pong.class);
    kryo.register(PlayerStats.class);
    kryo.register(PlayerStatsAll.class);
    kryo.register(TimeEvent.class);

    kryo.register(AdminCommand.class);
    kryo.register(BanPlayer.class);
    kryo.register(ChangeMap.class);
    kryo.register(ChangeNextMap.class);
    kryo.register(KickPlayer.class);
    kryo.register(RestartMap.class);
    kryo.register(SystemCommand.class);

    kryo.register(GameEvent.class);
    kryo.register(HitEvent.class);
    kryo.register(KillEvent.class);
    kryo.register(MoveEvent.class);
    kryo.register(RespawnEvent.class);
    kryo.register(ShootEvent.class);

    kryo.register(CommunicationMessage.class);
    kryo.register(ChatMessage.class);
    kryo.register(ServerMessage.class);

    kryo.register(UDPMessage.class);
    kryo.register(GameSnapshot.class);
    kryo.register(PlayerSnapshot.class);
    kryo.register(PlayerPosition.class);

    kryo.register(ServerStateType.class);
    kryo.register(BanReason.class);
    kryo.register(SystemCommandName.class);
    kryo.register(Point2D.class);
  }

}
