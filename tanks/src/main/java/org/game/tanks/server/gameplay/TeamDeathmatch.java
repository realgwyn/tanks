package org.game.tanks.server.gameplay;

import org.game.tanks.model.PlayerState;
import org.game.tanks.network.model.command.GiveMoney;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.process.SchedulerContext;
import org.game.tanks.server.model.PlayerServerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamDeathmatch extends GameType {

  @Autowired
  ServerContext serverContext;
  @Autowired
  SchedulerContext schedulerContext;

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
      serverContext.getOutgoingCommands().add(money);
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
      if (player.getModel().team == winningTeamNumber) {
        money.setValue(BIG_REWARD_VALUE);
      } else {
        money.setValue(SMALL_REWARD_VALUE);
      }
      serverContext.getOutgoingCommands().add(money);
    }
  }

  private boolean allTeamMembersDead(int teamNumber) {
    for (PlayerServerModel player : schedulerContext.getPlayers()) {
      if (player.getModel().team == teamNumber && player.getModel().state == PlayerState.ALIVE) {
        return false;
      }
    }
    return true;
  }

}
