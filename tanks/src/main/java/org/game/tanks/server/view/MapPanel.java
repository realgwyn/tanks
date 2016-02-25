package org.game.tanks.server.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.game.tanks.server.model.MapModel;
import org.game.tanks.server.model.MapObject;
import org.game.tanks.server.model.PlayerServerModel;
import org.game.tanks.utils.GraphicsUtils;
import org.springframework.stereotype.Component;

@Component
public class MapPanel extends JPanel {

  private List<PlayerServerModel> players;
  private MapModel map;

  public MapPanel() {
    players = new ArrayList<>();
    map = new MapModel();
    map.setHeight(1600);
    map.setWidth(1600);
    setBackground(Color.WHITE);
  }

  public void setPlayers(List<PlayerServerModel> players) {
    this.players = players;
  }

  public void setMapModel(MapModel map) {
    this.map = map;
  }

  public void refresh() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    int mapWidth = map.getWidth();
    int mapHeight = map.getHeight();
    float widthRatio = ((float) width) / ((float) mapWidth);
    float heightRatio = ((float) height) / ((float) mapHeight);

    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);

    int sectionSize = 200;
    int sectionsHorizontal = mapWidth / sectionSize;
    int sectionsVertical = mapHeight / sectionSize;
    int sectionWidth = width / sectionsHorizontal;
    int sectionHeight = height / sectionsVertical;
    g.setColor(Color.lightGray);
    for (int i = 0; i < sectionsVertical; i++) {
      g.drawLine(0, sectionHeight * i, width, sectionHeight * i);
    }
    for (int j = 0; j < sectionsHorizontal; j++) {
      g.drawLine(sectionWidth * j, 0, sectionWidth * j, height);
    }

    g.setColor(Color.black);
    g.drawRect(1, 1, width - 2, height - 2);
    for (MapObject object : map.getObjects()) {
      g.drawPolygon(GraphicsUtils.scale(object.getShape(), widthRatio, heightRatio));
    }
    for (PlayerServerModel player : players) {
      g.drawPolygon(GraphicsUtils.scale(player.getShape(), widthRatio, heightRatio));
    }

  }

}
