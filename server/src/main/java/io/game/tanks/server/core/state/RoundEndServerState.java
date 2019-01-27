package io.game.tanks.server.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.game.tanks.network.state.ServerStateType;
import io.game.tanks.server.core.ServerEngine;
import io.game.tanks.server.core.process.ProcessScheduler;
import io.game.tanks.server.gameplay.GameplayManager;

@Component
public class RoundEndServerState extends ServerState {

  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  RoundInitServerState roundInitState;
  @Autowired
  MatchEndServerState matchEndState;
  @Autowired
  WaitingForPlayersServerState waitingForPlayersState;
  @Autowired
  ServerEngine engine;
  @Autowired
  GameplayManager gameplayManager;

  private ServerState nextState;

  public RoundEndServerState() {
    super(ServerStateType.ROUND_END);
  }

  @Override
  public void onStateBegin() {
    // TODO: score team which won the round
    // TODO: send message which team won round

    if (gameplayManager.matchTimePassed()) {
      nextState = matchEndState;
    } else if (!gameplayManager.playersAreReadyForNewMatch()) {
      // If all players left the game
      nextState = waitingForPlayersState;
    } else {
      nextState = roundInitState;
    }

  }

  @Override
  public void update() {
    processScheduler.runProcesses();
    engine.setState(nextState);
  }

  @Override
  public void onStateEnd() {
    //
  }

}