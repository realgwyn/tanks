package io.tanks.server.core.task;

public class DatabaseTask {

  public enum DatabaseAction {
    CREATE, UPDATE, DELETE
  }

  private DatabaseAction action;
  private Object command;

  public DatabaseTask(Object command, DatabaseAction action) {
    this.command = command;
    this.action = action;
  }

  public DatabaseAction getAction() {
    return action;
  }

  public DatabaseTask setAction(DatabaseAction action) {
    this.action = action;
    return this;
  }

  public Object getCommand() {
    return command;
  }

}
