package org.game.tanks.database.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Permission {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "permissions")
  private Set<User> users;

  @Column(unique = true)
  private String name;

  public String getName() {
    return name;
  }

  public Permission setName(String name) {
    this.name = name;
    return this;
  }

  public Long getId() {
    return id;
  }

  public Permission setId(Long id) {
    this.id = id;
    return this;
  }

  public Set<User> getUsers() {
    return users;
  }

  public Permission setUsers(Set<User> users) {
    this.users = users;
    return this;
  }

}
