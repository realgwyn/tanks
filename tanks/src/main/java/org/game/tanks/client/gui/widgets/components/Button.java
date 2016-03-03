package org.game.tanks.client.gui.widgets.components;

import java.awt.Color;
import java.awt.Graphics;

import org.game.tanks.client.gui.widgets.GuiComponent;

@SuppressWarnings("serial")
public class Button extends GuiComponent {

  private Label label;
  private int labelOffsetX = 10;
  private int labelOffsetY = 10;
  private Color borderColor;

  public Button(String text) {
    this(new Label(text), Color.white);
  }

  public Button(String text, Color borderColor) {
    this(new Label(text), borderColor);
  }

  public Button(Label label, Color borderColor) {
    this.label = label;
    this.label.x = this.x + 10;
    this.label.y = this.y + 10;
    this.borderColor = borderColor;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(borderColor);
    g.drawRect(x, y, width, height);
    label.draw(g);
  }

}
