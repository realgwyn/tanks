package io.tanks.server.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import io.tanks.server.cfg.ServerConfig;
import io.tanks.server.view.ServerWindow;

//TODO: turn it into SpringBoot app
public class Server {

  @Autowired
  ServerWindow serverWindow;
  @Autowired
  ServerContext serverContext;
  @Autowired
  ServerConfig config;

  public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
    for (BeanDefinition bd : scanner.findCandidateComponents("io.tanks")) {
      ctx.register(Class.forName(bd.getBeanClassName()));
    }
    ctx.refresh();

    Server runner = (Server) ctx.getBean("serverRunner");
    runner.run();
  }

  public void run() {
    serverWindow.setVisible(true);
    serverWindow.setServerName(config.getProperty(ServerConfig.SERVER_DEFAULT_SERVER_NAME));
    serverWindow.setTcpPort(config.getPropertyInt(ServerConfig.SERVER_DEFAULT_TCP_PORT));
    serverWindow.setUdpPort(config.getPropertyInt(ServerConfig.SERVER_DEFAULT_UDP_PORT));
  }

}
