package org.game.tanks.cfg;

import java.awt.Font;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * Commonly used fonts and colors.
 * @author rafcio
 *
 */
@Component
public class GameStyle {
  
  private Font infoFont;
  private Font chatFont;
  private Font menuFont;
  private Font hudFont;

  @PostConstruct
  private void init() {
    try {
      chatFont = new Font("Courier New", Font.PLAIN, 12);
      infoFont = new Font("Courier New", Font.PLAIN, 10);

      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream("font_hud.ttf");
      
      Font hud = Font.createFont(Font.TRUETYPE_FONT, inputStream);
      hudFont = hud.deriveFont(Font.CENTER_BASELINE, 22);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Font getInfoFont() {
    return infoFont;
  }

  public Font getChatFont() {
    return chatFont;
  }

  public Font getMenuFont() {
    return menuFont;
  }

  public Font getHudFont() {
    return hudFont;
  }

}
