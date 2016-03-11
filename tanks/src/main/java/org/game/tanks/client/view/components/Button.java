package org.game.tanks.client.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.game.tanks.cfg.GameStyle;
import org.game.tanks.client.view.GuiComponent;

@SuppressWarnings("serial")
public class Button extends GuiComponent {

  private static int DEFAULT_WIDTH = 250;
  private static int DEFAULT_HEIGHT = 20;
  private int labelOffsetX = 10;
  private int labelOffsetY = 15;
  private Color borderColor;

  public Button(String text, Color borderColor) {
    this(new Label(text), borderColor, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public Button(String text, Font font) {
    this(new Label(text, font), GameStyle.WHITE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public Button(Label label, Color borderColor, int width, int height) {
    this.width = width;
    this.height = height;
    add(label, labelOffsetX, labelOffsetY);
    this.borderColor = borderColor;
  }

  @Override
  public void paintComponent(Graphics g) {
    g.setColor(borderColor);
    g.drawRect(x, y, width, height);
    super.paintComponent(g);
  }

}
