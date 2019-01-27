package io.tanks.server.database.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Stats stats;

  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
      @JoinColumn(name = "permission_id") })
  private Set<Permission> permissions;

  @OneToMany(mappedBy = "player")
  private Set<BanHistory> banHistory;

  @OneToMany(mappedBy = "player")
  private Set<BannedIp> bannedIps;

  @NotNull
  private String username;

  @NotNull
  private String hash;

  @NotNull
  @Email
  @Column(unique = true)
  private String email;

  @NotNull
  private Boolean banned = true;

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getHash() {
    return hash;
  }

  public User setHash(String hash) {
    this.hash = hash;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public Boolean getBanned() {
    return banned;
  }

  public User setBanned(Boolean banned) {
    this.banned = banned;
    return this;
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public User setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
    return this;
  }

  public Set<BanHistory> getBanHistory() {
    return banHistory;
  }

  public User setBanHistory(Set<BanHistory> banHistory) {
    this.banHistory = banHistory;
    return this;
  }

}
