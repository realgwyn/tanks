package io.game.tanks.server.core.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.game.tanks.network.model.command.PlayerInfo;
import io.game.tanks.network.model.game.MapModel;
import io.game.tanks.server.model.PlayerServerModel;

/**
 * All data over here should be ONLY accessed by ProcessScheduler, its not been made thread safe for performance
 * reasons.
 * 
 * @author rafal.kojta
 *
 */
@Component
public class SchedulerContext {

  private MapModel currentMap;
  private List<PlayerServerModel> players;
  private Map<Integer, PlayerServerModel> playerById;
  private List<PlayerInfo> playerStats;

  @PostConstruct
  public void init() {
    reinitialize();
  }

  public void reinitialize() {
    players = new ArrayList<>();
    playerById = new HashMap<>();
    playerStats = new ArrayList<>();
  }

  public List<PlayerServerModel> getPlayers() {
    return players;
  }

  public void addPlayer(PlayerServerModel newPlayer) {
    players.add(newPlayer);
    playerById.put(newPlayer.getConnectionId(), newPlayer);
  }

  public void removePlayer(long playerId) {
    Iterator<PlayerServerModel> it = players.iterator();
    while (it.hasNext()) {
      PlayerServerModel player = it.next();
      if (player.getConnectionId() == playerId) {
        players.remove(player);
        break;
      }
    }

    playerById.remove(playerId);
    playerStats.remove(playerId);
  }

  public PlayerServerModel getPlayerById(long id) {
    return playerById.get(id);
  }

  public MapModel getCurrentMap() {
    return currentMap;
  }

  public void setCurrentMap(MapModel currentMap) {
    this.currentMap = currentMap;
  }

}
