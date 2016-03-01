package org.game.tanks.server.core.task;

public class DatabaseTask extends Task {

  public enum DatabaseAction {
    CREATE, UPDATE, DELETE
  }

  private DatabaseAction action;

  public DatabaseTask(Object command, DatabaseAction action) {
    super(command);
    this.action = action;
  }

  public DatabaseAction getAction() {
    return action;
  }

  public DatabaseTask setAction(DatabaseAction action) {
    this.action = action;
    return this;
  }

}
