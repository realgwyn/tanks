package io.tanks.client.view.components;


import java.awt.*;

import io.tanks.client.view.GuiComponent;

@SuppressWarnings("serial")
public class Panel extends GuiComponent {

  private Color borderColor = Color.green;
  private Color backgroundColor = Color.black;
  private boolean borderVisible = true;

  @Override
  public void paintComponent(Graphics g) {
    g.setColor(backgroundColor);
    g.fillRect(x, y, width, height);
    if (borderVisible) {
      g.setColor(borderColor);
      g.drawRect(x, y, width, height);
    }
    super.paintComponent(g);
  }

  public Color getBorderColor() {
    return borderColor;
  }

  public void setBorderColor(Color borderColor) {
    this.borderColor = borderColor;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public boolean isBorderVisible() {
    return borderVisible;
  }

  public void setBorderVisible(boolean borderVisible) {
    this.borderVisible = borderVisible;
  }

}
