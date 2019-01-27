package io.tanks.client.cfg;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

/**
 * Commonly used fonts and colors.
 * 
 * @author rafcio
 */
@Component
public class GameStyle {

  public static Font FONT_BIG_MESSAGE = new Font("Courier New", Font.BOLD, 18);
  public static Font FONT_MENU_BUTTON = new Font("Courier New", Font.BOLD, 16);
  public static Font FONT_TEXT = new Font("Courier New", Font.PLAIN, 10);
  public static Font FONT_CHAT = new Font("Courier New", Font.PLAIN, 12);
  public static Font FONT_LABEL = new Font("Courier New", Font.BOLD, 12);
  public static Font FONT_LABEL_VALUE = new Font("Courier New", Font.PLAIN, 12);
  public static Font FONT_HUD;

  public static Color WHITE = new Color(243, 255, 246);
  public static Color BLACK = new Color(17, 17, 28);
  public static Color RED = new Color(237, 28, 36);
  public static Color GREEN = new Color(34, 177, 76);
  public static Color BLUE = new Color(0, 162, 232);
  public static Color ORANGE = new Color(255, 127, 39);
  public static Color GRAY = new Color(144, 144, 144);

  static {
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream("fonts/font_hud.ttf");

      Font hud;
      hud = Font.createFont(Font.TRUETYPE_FONT, inputStream);
      FONT_HUD = hud.deriveFont(Font.CENTER_BASELINE, 22);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
    }
  }

}
