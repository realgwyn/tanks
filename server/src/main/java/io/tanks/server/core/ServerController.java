package io.tanks.server.core;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.common.network.model.command.admin.BanPlayer;
import io.tanks.common.network.model.command.admin.ChangeMap;
import io.tanks.common.network.model.command.admin.KickPlayer;
import io.tanks.common.network.model.message.ChatMessage;
import io.tanks.server.core.state.MatchInitServerState;
import io.tanks.server.core.state.RoundInitServerState;
import io.tanks.server.database.DatabaseService;
import io.tanks.server.service.MapService;
import io.tanks.server.view.ServerWindow;

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
  ServerEventBus bus;
  @Autowired
  DatabaseService dbService;
  @Autowired
  MatchInitServerState matchInitState;
  @Autowired
  RoundInitServerState roundInitState;
  @Autowired
  MapService mapService;

  public void startServer(String serverName, int tcpPort, int udpPort) {
    serverWindow.setStatus("Starting...");
    ctx.setTcpPort(tcpPort);
    ctx.setUdpPort(udpPort);
    engine.start();
    while (engine.isReady()) {
      // Wait until engine is not ready
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    serverWindow.setStatus("Running");
  }

  public void stopServer() {
    serverWindow.setStatus("Stopping...");
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
