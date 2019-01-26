package org.game.tanks.client.view;


import java.awt.*;

import javax.annotation.PostConstruct;

import org.game.tanks.cfg.Config;
import org.game.tanks.client.view.components.Button;
import org.game.tanks.client.view.components.Label;
import org.game.tanks.client.view.components.MapPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class HudWindow extends GuiComponent {

  public static int DEFAULT_HEIGHT = 20;

  @Autowired
  Config cfg;

  // should this all children should be initialized outside ?
  private MapPanel mapPanel;
  private Button btnMenu;
  private Label lblHpValue;
  private Label lblAmmoValue;
  private Label lblFpsValue;

  @PostConstruct
  public void initialize() {
    width = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH);
    height = DEFAULT_HEIGHT;
    y = cfg.getPropertyInt(Config.GAME_RESOLUTION_WIDTH) - DEFAULT_HEIGHT;

    Label lblHp;
    Label lblAmmo;
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
