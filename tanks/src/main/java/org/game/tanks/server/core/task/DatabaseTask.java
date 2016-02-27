package org.game.tanks.server.core.task;

public class DatabaseTask {

  public enum DatabaseAction {
    CREATE, UPDATE, DELETE
  }

  private DatabaseAction action;
  private Object dataObject;

  public DatabaseAction getAction() {
    return action;
  }

  public DatabaseTask setAction(DatabaseAction action) {
    this.action = action;
    return this;
  }

  public Object getDataObject() {
    return dataObject;
  }

  public DatabaseTask setDataObject(Object dataObject) {
    this.dataObject = dataObject;
    return this;
  }

}
