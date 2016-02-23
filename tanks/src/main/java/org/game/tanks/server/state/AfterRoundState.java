package org.game.tanks.server.state;

import org.game.tanks.server.core.GameEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AfterRoundState extends ServerState{
  
  @Autowired
  private GameEventHandler gameEventHandler;

  @Override
  public void update(){
    gameEventHandler.processGameEvents();
    //display which team has won this round
    gameEventHandler.sendOutGameEvents();
  }

}