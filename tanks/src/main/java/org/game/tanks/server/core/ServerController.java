package org.game.tanks.server.core;

import org.game.tanks.database.service.DatabaseService;
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
  @Autowired
  DatabaseService dbService;

  public void actionKickPlayer(long playerId) {
    // TODO Auto-generated method stub

  }

  public void actionBanPlayer(long playerId) {
    // TODO Auto-generated method stub

  }

  public void startServer(String serverName, String tcpPort, String udpPort) {

    // dbService.createUser("username", "password", "username@com.pl");
    // System.out.println(dbService.getUserByUsername("username").getUsername());
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

}
