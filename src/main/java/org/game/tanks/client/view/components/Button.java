package org.game.tanks.client.view.components;


import java.awt.*;

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
  private org.game.tanks.client.view.components.Label label;
  private boolean borderEnabled = true;

  public Button(String text, Color color) {
    this(new org.game.tanks.client.view.components.Label(text, color), color, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public Button(String text, Font font) {
    this(new org.game.tanks.client.view.components.Label(text, font), GameStyle.WHITE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public Button(Label label, Color color, int width, int height) {
    this.width = width;
    this.height = height;
    this.label = label;
    this.label.setForegroundColor(color);
    add(this.label, labelOffsetX, labelOffsetY);
    this.borderColor = color;
    this.hoveringColor = GameStyle.GREEN;
    this.disabledColor = GameStyle.GRAY;
  }

  @Override
  public void paintComponent(Graphics g) {
    if (!enabled) {
      g.setColor(disabledColor);
      label.setForegroundColor(disabledColor);
    } else if (hovering) {
      g.setColor(hoveringColor);
      label.setForegroundColor(hoveringColor);
    } else {
      g.setColor(borderColor);
      label.setForegroundColor(borderColor);
    }
    if (borderEnabled) {
      g.drawRect(x, y, width, height);
    }
    super.paintComponent(g);
  }

  public void setBorderEnabled(boolean borderEnabled) {
    this.borderEnabled = borderEnabled;
  }

}
