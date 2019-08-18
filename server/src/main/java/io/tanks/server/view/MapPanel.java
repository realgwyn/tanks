package io.tanks.server.view;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.springframework.stereotype.Component;

import io.tanks.common.network.model.game.MapModel;
import io.tanks.common.network.model.game.MapObject;
import io.tanks.server.model.PlayerServerModel;
import io.tanks.common.core.utils.GraphicsUtils;

@Component
public class MapPanel extends JPanel {

  private List<PlayerServerModel> players;
  private MapModel mapModel;

  public MapPanel() {
    players = new ArrayList<>();
    mapModel = new MapModel();
    mapModel.setHeight(1600);
    mapModel.setWidth(1600);
    setBackground(Color.WHITE);
  }

  public void setPlayers(List<PlayerServerModel> players) {
    this.players = players;
  }

  public void setMapModel(MapModel mapModel) {
    this.mapModel = mapModel;
  }

  public void refresh() {
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    int mapWidth = mapModel.getWidth();
    int mapHeight = mapModel.getHeight();
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
    for (MapObject object : mapModel.getObjects()) {
      g.drawPolygon(GraphicsUtils.scale((Polygon) object.getShape(), widthRatio, heightRatio));
    }
    for (PlayerServerModel player : players) {
      g.drawPolygon(GraphicsUtils.scale(player.getModel().getShape(), widthRatio, heightRatio));
    }

  }

}
