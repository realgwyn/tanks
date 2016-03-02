package org.game.tanks.database.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.game.tanks.database.domain.MalformedPacketHistory;
import org.game.tanks.database.domain.UnauthorizedActionHistory;
import org.game.tanks.database.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@Component
@Transactional
public class DatabaseService {

  private Gson gson;

  @Autowired
  private SessionFactory sessionFactory;

  @PostConstruct
  public void init() {
    gson = new Gson();
  }

  public void saveMalformedPacket(MalformedPacketHistory message) {
    String json = gson.toJson(message.getObject());
    message.setSerializedObject(json);
    getSession().persist(message);
  }

  public void saveUnauthorizedAction(String ipAddress, Object commandObject) {
    String json = gson.toJson(commandObject);
    UnauthorizedActionHistory entity = new UnauthorizedActionHistory()
        .setIpAddress(ipAddress)
        .setSerializedObject(json)
        .setTime(new Date());
    getSession().persist(entity);
  }

  public User getUserByUsername(String username) {
    List<User> results = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
    if (!results.isEmpty()) {
      return results.get(0);
    }
    return null;
  }

  public User createUser(String username, String hash, String email) {
    User user = new User()
        .setEmail(email)
        .setHash(hash)
        .setUsername(username);
    getSession().persist(user);
    return user;
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

}
