package org.game.tanks.server.state;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.network.model.command.SyncTime;
import org.game.tanks.network.model.message.ServerMessage;
import org.game.tanks.server.core.ServerContext;
import org.game.tanks.server.core.ServerEngine;
import org.game.tanks.server.core.process.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitingForPlayersState extends ServerState {

  @Autowired
  ServerContext ctx;
  @Autowired
  ProcessScheduler processScheduler;
  @Autowired
  ServerEngine engine;
  @Autowired
  BeforeRoundState beforeRoundState;
  @Autowired
  Config config;

  private int stateFinishDuration = 3000;
  private int matchDuration = 15;
  private int roundDuration = 2;

  public WaitingForPlayersState() {
    super(ServerStateType.WAITING_FOR_PLAYERS);
  }

  @PostConstruct
  public void init(){
    matchDuration = config.getPropertyInt(Config.SERVER_MATCH_DURATION);
    roundDuration = config.getPropertyInt(Config.SERVER_ROUND_DURATION);
  }

  @Override
  public void onStateBegin() {

  }

  @Override
  public void update() {
    processScheduler.runProcesses();

    // display info that points will not start until there will be more than one player in the game

    if (ctx.getPlayers().size() > 1) {
      engine.setState(beforeRoundState);
    }
  }

  @Override
  public void onStateEnd(){
    ServerMessage msg = new ServerMessage();
    msg.setText("Match Roud will begin in " + stateFinishDuration/1000 + " seconds.");
    ctx.getOutgoingCommunicationMessages().add(msg);
    long currentTime = System.currentTimeMillis();
    ctx.getMatchStartTime()
    ctx.getMatchEndTime()
    ctx.getRoundEndTime()
    SyncTime syncTime = new SyncTime()
        .setMatchStartTime(matchStartTime)
        .setMatchEndTime(matchEndTime)
        .setRoundEndTime();
    ctx.getOutgoingCommands().add(e)
  }

}
