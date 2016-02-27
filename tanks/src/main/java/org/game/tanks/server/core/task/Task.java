package org.game.tanks.server.core.task;

public class Task {

  private Object command;

  public Task(Object command){
    this.command = command;
  }

  public Object getCommand() {
    return command;
  }

}
