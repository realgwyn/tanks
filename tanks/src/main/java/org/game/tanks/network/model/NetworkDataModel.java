package org.game.tanks.network.model;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import org.game.tanks.model.MapObject;
import org.game.tanks.model.PlayerModel;
import org.game.tanks.model.PlayerState;
import org.game.tanks.network.model.command.ChangeName;
import org.game.tanks.network.model.command.ChangeState;
import org.game.tanks.network.model.command.ChatHistory;
import org.game.tanks.network.model.command.ChooseTeam;
import org.game.tanks.network.model.command.Connect;
import org.game.tanks.network.model.command.Disconnect;
import org.game.tanks.network.model.command.GameInitData;
import org.game.tanks.network.model.command.GiveItem;
import org.game.tanks.network.model.command.GiveModifier;
import org.game.tanks.network.model.command.GiveMoney;
import org.game.tanks.network.model.command.Latency;
import org.game.tanks.network.model.command.MapInfoData;
import org.game.tanks.network.model.command.Ping;
import org.game.tanks.network.model.command.PlayerInfo;
import org.game.tanks.network.model.command.PlayersLatency;
import org.game.tanks.network.model.command.SyncTime;
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
import org.game.tanks.server.core.state.ServerState.ServerStateType;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class NetworkDataModel {

  public static void register(EndPoint endpoint) {
    Kryo kryo = endpoint.getKryo();
    kryo.register(TCPMessage.class);
    kryo.register(Handshake.class);

    kryo.register(Command.class);
    kryo.register(ChangeName.class);
    kryo.register(ChangeState.class);
    kryo.register(ChatHistory.class);
    kryo.register(ChooseTeam.class);
    kryo.register(Connect.class);
    kryo.register(Disconnect.class);
    kryo.register(GameInitData.class);
    kryo.register(GiveItem.class);
    kryo.register(GiveModifier.class);
    kryo.register(GiveMoney.class);
    kryo.register(Latency.class);
    kryo.register(MapInfoData.class);
    kryo.register(MapObject.class);
    kryo.register(PlayersLatency.class);
    kryo.register(Ping.class);
    kryo.register(PlayerInfo.class);
    kryo.register(SyncTime.class);

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
    kryo.register(Polygon.class);
    kryo.register(PlayerState.class);
    kryo.register(PlayerModel.class);
  }

}
