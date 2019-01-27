package io.tanks.client.view.menu;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;

import io.tanks.client.cfg.ClientConfig;
import io.tanks.client.cfg.GameStyle;
import io.tanks.client.core.GameEngine;
import io.tanks.client.state.menu.MainMenuState;
import io.tanks.client.view.GuiComponent;
import io.tanks.client.view.components.Button;
import io.tanks.client.view.components.Label;
import io.tanks.client.view.components.Label.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
@SuppressWarnings("serial")
public class OptionsMenuWindow extends GuiComponent {

  @Autowired
  ClientConfig cfg;
  @Autowired
  GameEngine engine;
  @Autowired
  MainMenuState mainMenuState;

  private Button btnCancel;

  @PostConstruct
  public void initialize() {
    initComponents();
    initActions();
  }

  private void initComponents() {
    width = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_WIDTH);
    height = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_HEIGHT);

    Label label = new Label("Options", GameStyle.FONT_BIG_MESSAGE);
    label.setHorizontalAlignment(HorizontalAlignment.CENTER);
    add(label, width / 2, 20);

    btnCancel = new Button("ESC - Cancel", GameStyle.FONT_MENU_BUTTON);
    btnCancel.setBorderEnabled(false);
    add(btnCancel, 20, height - 40);

  }

  private void initActions() {
    btnCancel.setMouseActionListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        engine.setState(mainMenuState);
      }
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_ESCAPE:
      engine.setState(mainMenuState);
      break;
    default:
      break;
    }
  }

}