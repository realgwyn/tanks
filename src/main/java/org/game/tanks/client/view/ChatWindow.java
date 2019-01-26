package org.game.tanks.client.view;


import java.awt.*;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;

import org.game.tanks.client.core.ClientContext;
import org.game.tanks.client.core.GameDisplay;
import org.game.tanks.client.core.GuiManager;
import org.game.tanks.client.service.MessageService;
import org.game.tanks.network.model.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class ChatWindow extends GuiComponent {

  @Autowired
  GameDisplay display;
  @Autowired
  GuiManager guiManager;
  @Autowired
  MessageService messageService;
  @Autowired
  ClientContext gameContext;

  @PostConstruct
  public void initialize() {
    // TODO Auto-generated method stub
  }

  @Override
  public void paintComponent(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, width, height);
    g.setColor(Color.green);
    g.drawRect(x, y, width, height);
    super.paintComponent(g);
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

  private void sendChatMessage(String chatMessage) {
    ChatMessage message = new ChatMessage();
    messageService.sendMessage(message);
  }

}
