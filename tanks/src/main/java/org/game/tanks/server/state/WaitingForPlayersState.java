package org.game.tanks.server.state;

import org.springframework.stereotype.Component;

@Component
public class WaitingForPlayersState extends ServerState{

  @Override
  public void update() {
    //display info that points will not start until there will be more than one player in the game
    
  }

}
