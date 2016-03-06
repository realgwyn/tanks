package org.game.tanks.server.core;

import org.game.tanks.cfg.Config;
import org.game.tanks.server.view.ServerWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

public class Server {

  @Autowired
  ServerWindow serverWindow;
  @Autowired
  ServerContext serverContext;
  @Autowired
  Config config;

  public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
    for (BeanDefinition bd : scanner.findCandidateComponents("org.game.tanks")) {
      ctx.register(Class.forName(bd.getBeanClassName()));
    }
    ctx.refresh();

    Server runner = (Server) ctx.getBean("serverRunner");
    runner.run();
  }

  public void run() {
    serverWindow.setVisible(true);
    serverWindow.setServerName(config.getProperty(Config.SERVER_DEFAULT_SERVER_NAME));
    serverWindow.setTcpPort(config.getPropertyInt(Config.SERVER_DEFAULT_TCP_PORT));
    serverWindow.setUdpPort(config.getPropertyInt(Config.SERVER_DEFAULT_UDP_PORT));
  }

}
