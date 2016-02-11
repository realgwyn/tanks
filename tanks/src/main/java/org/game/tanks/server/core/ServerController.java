package org.game.tanks.server.core;

import org.game.tanks.server.view.ServerWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerController {

  @Autowired
  ServerEngine engine;
  @Autowired
  ServerConfig config;
  @Autowired
  ServerWindow serverWindow;

  public void actionKickPlayer(long playerId) {
    // TODO Auto-generated method stub

  }

  public void actionBanPlayer(long playerId) {
    // TODO Auto-generated method stub

  }

  public void startServer(String serverName, String tcpPort, String udpPort) {
    try{
      config.setTcpPort(Integer.parseInt(tcpPort));
      config.setUdpPort(Integer.parseInt(udpPort));
    }catch(Exception e){
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
