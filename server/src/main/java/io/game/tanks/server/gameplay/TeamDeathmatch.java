package io.game.tanks.server.gameplay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.model.command.GiveMoney;
import io.game.tanks.network.state.PlayerState;
import io.game.tanks.server.core.ServerContext;
import io.game.tanks.server.core.ServerEventBus;
import io.game.tanks.server.core.process.SchedulerContext;
import io.game.tanks.server.model.PlayerServerModel;
import io.game.tanks.server.service.MapService;

@Component
public class TeamDeathmatch extends GameType {

  @Autowired
  ServerContext serverContext;
  @Autowired
  ServerEventBus bus;
  @Autowired
  SchedulerContext schedulerContext;
  @Autowired
  MapService mapService;

  private final int TEAM_A = 1;
  private final int TEAM_B = 2;
  private int winningTeamNumber;
  private final int SMALL_REWARD_VALUE = 800;
  private final int BIG_REWARD_VALUE = 1600;

  @Override
  public void reinitialize() {
    winningTeamNumber = 0;
  }

  @Override
  public void initializePlayersProperties(List<PlayerServerModel> players) {
    for (PlayerServerModel player : players) {
      player.setHealth(100);
      player.setState(PlayerState.ALIVE);
    }
  }

  @Override
  public boolean roundObjectivesCompleted() {

    if (allTeamMembersDead(TEAM_A)) {
      winningTeamNumber = TEAM_B;
      return true;
    }

    if (allTeamMembersDead(TEAM_B)) {
      winningTeamNumber = TEAM_A;
      return true;
    }

    return false;
  }

  /**
   * Both teams get little bit money
   */
  @Override
  public void scoreRoundTimeout() {
    for (PlayerServerModel player : schedulerContext.getPlayers()) {
      GiveMoney money = new GiveMoney();
      money.setPlayerToId(player.getConnectionId());
      money.setValue(SMALL_REWARD_VALUE);
      bus.getOutgoingCommands().add(money);
    }
  }

  /**
   * Winning team gets more money than loosing team
   */
  @Override
  public void scoreRound() {
    for (PlayerServerModel player : schedulerContext.getPlayers()) {
      GiveMoney money = new GiveMoney();
      money.setPlayerToId(player.getConnectionId());
      if (player.getTeam() == winningTeamNumber) {
        money.setValue(BIG_REWARD_VALUE);
      } else {
        money.setValue(SMALL_REWARD_VALUE);
      }
      bus.getOutgoingCommands().add(money);
    }
  }

  private boolean allTeamMembersDead(int teamNumber) {
    for (PlayerServerModel player : schedulerContext.getPlayers()) {
      if (player.getTeam() == teamNumber && player.getState() == PlayerState.ALIVE) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean playersAreReadyForNewMatch() {
    // TODO both teams has players
    return true;
  }

}
