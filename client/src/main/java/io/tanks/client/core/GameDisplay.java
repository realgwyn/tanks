package io.tanks.client.core;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.tanks.client.cfg.ClientConfig;

@Component
public class GameDisplay extends Canvas {

  private static final long serialVersionUID = 1L;
  private BufferedImage image;
  private int[] rasterPixels;
  private JFrame frame;

  public int WIDTH = 200;
  public int HEIGHT = 150;
  public int SCALE = 2;

  @Autowired
  ClientConfig config;

  @PostConstruct
  public void init() {
    WIDTH = config.getPropertyInt(ClientConfig.GAME_RESOLUTION_WIDTH);
    HEIGHT = config.getPropertyInt(ClientConfig.GAME_RESOLUTION_HEIGHT);
    SCALE = config.getPropertyInt(ClientConfig.GAME_RESOLUTION_SCALE);
    // XXX image resized by +1 width and height:
    image = new BufferedImage(WIDTH + 1, HEIGHT + 1, BufferedImage.TYPE_INT_RGB);
    rasterPixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    frame = new JFrame(config.getProperty(ClientConfig.GAME_NAME));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(this, BorderLayout.CENTER);
  }

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    g.dispose();
    bs.show();
  }

  public void clearScreen() {
    for (int i = 0; i < rasterPixels.length; i++) {
      rasterPixels[i] = 0;
    }
  }

  public int[] getRasterPixels() {
    return rasterPixels;
  }

  @Override
  public Graphics getGraphics() {
    return image.getGraphics();
  }

  public JFrame getWindow() {
    return frame;
  }

}
