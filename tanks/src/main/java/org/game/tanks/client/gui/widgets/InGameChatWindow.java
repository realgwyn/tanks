package org.game.tanks.client.gui.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.client.core.GameContext;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.core.MessageService;
import org.game.tanks.network.model.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class InGameChatWindow extends GuiComponent {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  MessageService messageService;
  @Autowired
  GameContext gameContext;

  @PostConstruct
  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, width, height);
    g.setColor(Color.green);
    g.drawRect(x, y, width, height);
    super.draw(g);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ENTER:
      guiManager.hideComponent(this);
      sendChatMessage("wololo");
      break;
    case KeyEvent.VK_ESCAPE:
      guiManager.hideComponent(this);
      break;
    default:

      break;
    }
  }

  @Override
  public void setVisible(boolean visible) {
    // TODO Auto-generated method stub
    super.setVisible(visible);
  }

  private void sendChatMessage(String chatMessage) {
    ChatMessage message = new ChatMessage();
    messageService.sendMessage(message);
  }

}
