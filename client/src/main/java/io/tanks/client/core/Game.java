package io.tanks.client.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

public class Game {

  @Autowired
  GameEngine engine;
  
  public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    try {

      ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
      for (BeanDefinition bd : scanner.findCandidateComponents("io.tanks")) {
        ctx.register(Class.forName(bd.getBeanClassName()));
      }
      ctx.refresh();

      Game runner = (Game) ctx.getBean("gameRunner");
      runner.run();
    } catch (Exception e) {
      e.printStackTrace();

      // TODO: game crashed, ask user if he wants to send crash report (email)
      ctx.close();
      ctx.destroy();
      System.exit(-1);
    }
  }

  public void run() {
    engine.start();
  }

}
