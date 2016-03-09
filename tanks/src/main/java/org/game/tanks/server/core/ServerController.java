package org.game.tanks.server.core;

import java.util.Date;

import org.apache.log4j.Logger;
import org.game.tanks.database.service.DatabaseService;
import org.game.tanks.network.model.command.admin.BanPlayer;
import org.game.tanks.network.model.command.admin.ChangeMap;
import org.game.tanks.network.model.command.admin.KickPlayer;
import org.game.tanks.network.model.message.ChatMessage;
import org.game.tanks.server.core.state.MatchInitServerState;
import org.game.tanks.server.core.state.RoundInitServerState;
import org.game.tanks.server.service.MapService;
import org.game.tanks.server.view.ServerWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerController {

  private final static Logger logger = Logger.getLogger(ServerController.class);

  @Autowired
  ServerEngine engine;
  @Autowired
  ServerWindow serverWindow;
  @Autowired
  ServerContext ctx;
  @Autowired
  EventBus bus;
  @Autowired
  DatabaseService dbService;
  @Autowired
  MatchInitServerState matchInitState;
  @Autowired
  RoundInitServerState roundInitState;
  @Autowired
  MapService mapService;

  public void startServer(String serverName, String tcpPort, String udpPort) {
    try {
      ctx.setTcpPort(Integer.parseInt(tcpPort));
      ctx.setUdpPort(Integer.parseInt(udpPort));
    } catch (Exception e) {
      serverWindow.setStatus("Invalid port");
      e.printStackTrace();
      return;
    }

    engine.start();
    serverWindow.setStatus("Running");
  }

  public void stopServer() {
    engine.stop();
    serverWindow.setStatus("Stopped");
  }

  public void restartMatch() {
    logger.debug("Restarting Match");
    engine.setState(matchInitState);
  }

  public void restartRound() {
    logger.debug("Restarting Round");
    engine.setState(roundInitState);
  }

  public void changeMap(String mapName) {
    logger.debug("Changed Map");
    bus.getIncomingAdminCommands().add(new ChangeMap().setMapName(mapName));
  }

  public void sendChatMessage(String message) {
    logger.debug("Sent Chat Message");
    bus.getOutgoingCommunicationMessages().add(new ChatMessage().setText(message).setTime(new Date()));
  }

  public void actionKickPlayer(KickPlayer command) {
    logger.debug("Kicked Player");
    bus.getIncomingAdminCommands().add(command);
  }

  public void actionBanPlayer(BanPlayer command) {
    logger.debug("Banned Player");
    bus.getIncomingAdminCommands().add(command);
  }

}
