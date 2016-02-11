package org.game.tanks.server.core;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.game.tanks.server.model.MapModel;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.stereotype.Component;

@Component
public class ServerContext {

  private ConcurrentLinkedQueue<PlayerServerModel> players;
  private MapModel currentMap;
  private MapModel nextMap;

  @PostConstruct
  public void init() {
    players = new ConcurrentLinkedQueue<>();
    currentMap = new MapModel();
    nextMap = new MapModel();
  }

  public ConcurrentLinkedQueue<PlayerServerModel> getPlayers() {
    return players;
  }

  public void setPlayers(ConcurrentLinkedQueue<PlayerServerModel> players) {
    this.players = players;
  }

  public MapModel getCurrentMap() {
    return currentMap;
  }

  public void setCurrentMap(MapModel currentMap) {
    this.currentMap = currentMap;
  }

  public MapModel getNextMap() {
    return nextMap;
  }

  public void setNextMap(MapModel nextMap) {
    this.nextMap = nextMap;
  }

}
