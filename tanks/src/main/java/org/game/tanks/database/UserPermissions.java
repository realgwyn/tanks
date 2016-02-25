package org.game.tanks.database;

import java.security.Permissions;

public class UserPermissions {

  private User user;
  private Permissions permissions;

  public User getUser() {
    return user;
  }

  public UserPermissions setUser(User user) {
    this.user = user;
    return this;
  }

  public Permissions getPermissions() {
    return permissions;
  }

  public UserPermissions setPermissions(Permissions permissions) {
    this.permissions = permissions;
    return this;
  }

}
