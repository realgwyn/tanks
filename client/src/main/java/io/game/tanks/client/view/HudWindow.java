package io.game.tanks.client.view;


import java.awt.*;

import javax.annotation.PostConstruct;

import io.game.tanks.cfg.ClientConfig;
import io.game.tanks.client.view.components.Label;
import io.game.tanks.client.view.components.MapPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class HudWindow extends GuiComponent {

  public static int DEFAULT_HEIGHT = 20;

  @Autowired
  ClientConfig cfg;

  // should this all children should be initialized outside ?
  private MapPanel mapPanel;
  private io.game.tanks.client.view.components.Button btnMenu;
  private io.game.tanks.client.view.components.Label lblHpValue;
  private io.game.tanks.client.view.components.Label lblAmmoValue;
  private io.game.tanks.client.view.components.Label lblFpsValue;

  @PostConstruct
  public void initialize() {
    width = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_WIDTH);
    height = DEFAULT_HEIGHT;
    y = cfg.getPropertyInt(ClientConfig.GAME_RESOLUTION_WIDTH) - DEFAULT_HEIGHT;

    io.game.tanks.client.view.components.Label lblHp;
    io.game.tanks.client.view.components.Label lblAmmo;
    Label lblFps;
    // TODO Auto-generated method stub
  }

  @Override
  public void paintComponent(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(x, y, width, height);
    g.setColor(Color.green);
    g.drawRect(x, y - 60, width, height);
    super.paintComponent(g);
  }

}
