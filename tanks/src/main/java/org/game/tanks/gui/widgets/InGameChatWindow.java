package org.game.tanks.gui.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.game.tanks.core.GameContext;
import org.game.tanks.core.GameDisplay;
import org.game.tanks.core.GuiManager;
import org.game.tanks.core.MessageService;
import org.game.tanks.network.model.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InGameChatWindow extends GuiComponent{

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired 
  MessageService messageService;
  @Autowired
  GameContext gameContext;
  
  @Override
  public void draw() {
    Graphics g = display.getGraphics();
    g.setColor(Color.black);
    g.fillRect(10, display.HEIGHT - 60, display.WIDTH - 20, 50);
    g.setColor(Color.green);
    g.drawRect(10, display.HEIGHT - 60, display.WIDTH - 20, 50);
    g.drawString("Write Chat:", 20, display.HEIGHT - 45);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ENTER:
      guiManager.removeComponent(this);
      sendChatMessage("wololo");
      break;
    case KeyEvent.VK_ESCAPE:
      guiManager.removeComponent(this);
      break;
    default:
      
      break;
    }
  }

  private void sendChatMessage(String chatMessage) {
    ChatMessage message = new ChatMessage();
    messageService.sendMessage(message);
  }

  
}
