package org.game.tanks.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

public class Game {

  @Autowired
  GameEngine engine;

  public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
    for (BeanDefinition bd : scanner.findCandidateComponents("org.game.tanks")) {
      ctx.register(Class.forName(bd.getBeanClassName()));
    }
    ctx.refresh();

    Game runner = (Game) ctx.getBean("mainRunner");
    runner.run();
  }

  public void run() {
    engine.start();
  }

}
