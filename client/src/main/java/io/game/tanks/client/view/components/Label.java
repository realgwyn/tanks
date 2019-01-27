package io.game.tanks.client.view.components;


import java.awt.*;

import io.game.tanks.cfg.GameStyle;
import io.game.tanks.client.view.GuiComponent;

@SuppressWarnings("serial")
public class Label extends GuiComponent {

  private String text;
  private Color foregroundColor;
  private Font font;
  private HorizontalAlignment horizontalAlignment;
  private int positionX;
  boolean positionResolved = false;

  public enum HorizontalAlignment {
    LEFT, CENTER, RIGHT
  }

  public Label(String text) {
    this(text, GameStyle.WHITE);
  }

  public Label(String text, Font font) {
    this(text, GameStyle.WHITE, font);
  }

  public Label(String text, Color foreground) {
    this(text, foreground, GameStyle.FONT_LABEL);
  }

  public Label(String text, Color foreground, Font font) {
    this.text = text;
    this.foregroundColor = foreground;
    this.font = font;
    this.horizontalAlignment = HorizontalAlignment.LEFT;
    this.positionX = this.x;
  }

  @Override
  public void paintComponent(Graphics g) {

    Font originalFont = g.getFont();
    Color originalColor = g.getColor();
    g.setFont(font);
    g.setColor(foregroundColor);

    if (!positionResolved) {
      positionResolved = true;
      this.positionX = resolvePositionX(g);
    }
    g.drawString(text, positionX, y);

    g.setFont(originalFont);
    g.setColor(originalColor);
  }

  private int resolvePositionX(Graphics g) {
    int width = g.getFontMetrics(font).stringWidth(text);
    switch (horizontalAlignment) {
    default:
    case LEFT:
      return this.x;
    case CENTER:
      return x - width / 2;
    case RIGHT:
      return x - width;
    }
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setForegroundColor(Color foregroundColor) {
    this.foregroundColor = foregroundColor;
  }

  public void setFont(Font font) {
    this.font = font;
  }

  public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
    this.horizontalAlignment = horizontalAlignment;
    this.positionResolved = false;

  }

}
