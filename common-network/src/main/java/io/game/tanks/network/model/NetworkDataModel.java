package io.game.tanks.network.model;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import io.game.tanks.network.model.command.ChangeName;
import io.game.tanks.network.model.command.ChangeState;
import io.game.tanks.network.model.command.ChatHistory;
import io.game.tanks.network.model.command.ChooseTeam;
import io.game.tanks.network.model.command.Connect;
import io.game.tanks.network.model.command.Disconnect;
import io.game.tanks.network.model.command.GameInitData;
import io.game.tanks.network.model.command.GiveItem;
import io.game.tanks.network.model.command.GiveModifier;
import io.game.tanks.network.model.command.GiveMoney;
import io.game.tanks.network.model.command.Handshake;
import io.game.tanks.network.model.command.Latency;
import io.game.tanks.network.model.command.MapInfoData;
import io.game.tanks.network.model.command.Ping;
import io.game.tanks.network.model.command.PlayerInfo;
import io.game.tanks.network.model.command.PlayersLatency;
import io.game.tanks.network.model.command.SyncTime;
import io.game.tanks.network.model.command.admin.BanPlayer;
import io.game.tanks.network.model.command.admin.ChangeMap;
import io.game.tanks.network.model.command.admin.ChangeNextMap;
import io.game.tanks.network.model.command.admin.KickPlayer;
import io.game.tanks.network.model.command.admin.RestartMatch;
import io.game.tanks.network.model.command.admin.RestartRound;
import io.game.tanks.network.model.command.admin.SystemCommand;
import io.game.tanks.network.model.event.HitEvent;
import io.game.tanks.network.model.event.KillEvent;
import io.game.tanks.network.model.event.MoveEvent;
import io.game.tanks.network.model.event.RespawnEvent;
import io.game.tanks.network.model.event.ShootEvent;
import io.game.tanks.network.model.game.MapObject;
import io.game.tanks.network.model.game.PlayerModel;
import io.game.tanks.network.model.message.ChatMessage;
import io.game.tanks.network.model.message.ServerMessage;
import io.game.tanks.network.model.udp.GameSnapshot;
import io.game.tanks.network.model.udp.PlayerPosition;
import io.game.tanks.network.model.udp.PlayerSnapshot;
import io.game.tanks.network.state.ClientStateType;
import io.game.tanks.network.state.PlayerState;
import io.game.tanks.network.state.ServerStateType;

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
    kryo.register(RestartMatch.class);
    kryo.register(RestartRound.class);
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
    kryo.register(BanPlayer.BanReason.class);
    kryo.register(SystemCommand.SystemCommandName.class);
    kryo.register(Point2D.class);
    kryo.register(Polygon.class);
    kryo.register(PlayerState.class);
    kryo.register(PlayerModel.class);
    kryo.register(MapObject[].class);
    kryo.register(Rectangle.class);
    kryo.register(ArrayList.class);
    kryo.register(PlayerPosition[].class);
    kryo.register(ClientStateType.class);
    kryo.register(Latency[].class);
  }

}
