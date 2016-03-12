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
  private Color disabledColor;
  private Color hoveringColor;

  public Button(String text, Color color) {
    this(new Label(text, color), color, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public Button(String text, Font font) {
    this(new Label(text, font), GameStyle.ORANGE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public Button(Label label, Color color, int width, int height) {
    this.width = width;
    this.height = height;
    label.setForegroundColor(color);
    add(label, labelOffsetX, labelOffsetY);
    this.borderColor = color;
    this.hoveringColor = color.brighter();
    this.disabledColor = color.darker();
  }

  @Override
  public void paintComponent(Graphics g) {
    if (!enabled) {
      g.setColor(disabledColor);
    } else if (hovering) {
      g.setColor(hoveringColor);
    } else {
      g.setColor(borderColor);
    }
    if (focused) {
    }
    g.drawRect(x, y, width, height);
    super.paintComponent(g);
  }

}
