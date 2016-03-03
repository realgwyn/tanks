package org.game.tanks.client.gui.widgets.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.game.tanks.client.gui.widgets.GuiComponent;

@SuppressWarnings("serial")
public class Label extends GuiComponent {

  public Label(String text) {
    this(text, Color.white, new Font("Consolas", Font.PLAIN, 12));
  }

  public Label(String text, Color foreground) {
    this(text, foreground, new Font("Consolas", Font.PLAIN, 12));
  }

  public Label(String text, Color foreground, Font font) {
    this.text = text;
    this.foregroundColor = foreground;
    this.font = font;
  }

  private String text;
  private Color foregroundColor;
  private Font font;

  @Override
  public void draw(Graphics g) {
    Font originalFont = g.getFont();
    Color originalColor = g.getColor();
    g.setFont(font);
    g.setColor(foregroundColor);
    g.drawString(text, x, y);
    g.setFont(originalFont);
    g.setColor(originalColor);
  }

}
