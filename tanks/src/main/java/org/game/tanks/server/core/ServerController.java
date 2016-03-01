package org.game.tanks.server.core;

import org.game.tanks.server.view.ServerWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerController {

  @Autowired
  ServerEngine engine;
  @Autowired
  ServerWindow serverWindow;
  @Autowired
  ServerContext ctx;

  public void actionKickPlayer(long playerId) {
    // TODO Auto-generated method stub

  }

  public void actionBanPlayer(long playerId) {
    // TODO Auto-generated method stub

  }

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

  public void sendChatMessage(String message) {
    // TODO Auto-generated method stub

  }

  // TODO
  public void initNewMatch() {
    int matchDuration = ctx.getMatchDuration();
    int roundDuration = ctx.getRoundDuration();

    // setup context data
    // send some events to players
    // reconnect players from last match if any exists
    // reinitialize context
  }

  // TODO
  public void initNewRound() {
    int roundDuration = ctx.getRoundDuration();
    // setup context data
    // setup players positions
    // send some time sync data to players
  }

}
