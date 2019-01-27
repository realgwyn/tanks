package io.game.tanks.client.view.components;


import java.awt.*;

import javax.annotation.PostConstruct;

import io.game.tanks.cfg.ClientConfig;
import io.game.tanks.cfg.GameStyle;
import io.game.tanks.client.core.GuiManager;
import io.game.tanks.client.view.GuiComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class MessageWindow extends GuiComponent {

  @Autowired
  GuiManager guiManager;
  @Autowired
  ClientConfig cfg;

  private Label label;
  private int displayTimeFrames = 300;
  private int displayTimeCount = 0;

  @PostConstruct
  public void init() {
    label = new Label("", GameStyle.WHITE, GameStyle.FONT_BIG_MESSAGE);
    focusable = false;
    label.x = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_WIDTH) / 2;
    label.y = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_HEIGHT) / 2 - 70;
    label.setHorizontalAlignment(Label.HorizontalAlignment.CENTER);
  }

  @Override
  public void paintComponent(Graphics g) {
    label.paintComponent(g);
  }

  public void showInfoMessage(String text) {
    displayTimeCount = 0;
    label.setText(text);
    label.setForegroundColor(GameStyle.BLUE);
    guiManager.showComponent(this);
  }

  public void showWarningMessage(String text) {
    displayTimeCount = 0;
    label.setText(text);
    label.setForegroundColor(GameStyle.ORANGE);
    guiManager.showComponent(this);
  }

  public void showErrorMessage(String text) {
    displayTimeCount = 0;
    label.setText(text);
    label.setForegroundColor(GameStyle.RED);
    guiManager.showComponent(this);
  }

  @Override
  public void update() {
    if (isVisible()) {
      displayTimeCount++;
      if (displayTimeCount > displayTimeFrames) {
        guiManager.hideComponent(this);
        return;
      }
    }
  }

}
