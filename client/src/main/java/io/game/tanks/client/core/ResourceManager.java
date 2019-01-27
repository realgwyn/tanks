package io.game.tanks.client.core;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import io.game.tanks.cfg.ImageName;
import io.game.tanks.cfg.MessageName;
import io.game.tanks.cfg.SpriteName;
import io.game.tanks.client.model.Sprite;
import org.springframework.stereotype.Component;

@Component
public class ResourceManager {

  private HashMap<String, BufferedImage> images;
  private HashMap<String, String> messages;
  private HashMap<String, Sprite> sprites;
  
  @PostConstruct
  public void init(){
    loadMessages();
    loadImages();
    loadSprites();
  }

  public BufferedImage getImage(String imageName) {
     BufferedImage image = null;
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      URL resource = classLoader.getResource("img/"+imageName);
      if(resource == null){
        throw new RuntimeException("File not found in resource path " + imageName);
      }
      image = ImageIO.read(resource);
    } catch (IOException ex) {
      System.out.println(ex);
    }
    return image;
  }

  /**
   * Loads text file lines to String list
   */
  public static List<String> getFileLines(String fileName) {
    ArrayList<String> lines = new ArrayList<String>();
    BufferedReader reader = null;
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream(fileName);
      if(inputStream == null){
        throw new RuntimeException("File not found in resource path " + fileName);
      }
      reader = new BufferedReader(new InputStreamReader(inputStream));

      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null)
          reader.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return lines;
  }

  public BufferedImage getImage(ImageName name) {
    return images.get(name.toString());
  }

  public Sprite getSprite(SpriteName name) {
    return sprites.get(name.toString());
  }

  public String getMessage(MessageName name) {
    return messages.get(name.toString());
  }

  private void loadMessages() {
    messages = new HashMap<>();
    List<String> lines = getFileLines("messages.properties");
    for (String line : lines) {
      if (line.startsWith("#"))
        continue;
      String[] tokens = line.split("=");
      if (tokens.length == 2) {
        messages.put(tokens[0].trim(), tokens[1].trim());
      }
    }
  }

  private void loadImages() {
    images = new HashMap<>();
    List<String> lines = getFileLines("images.properties");
    for (String line : lines) {
      if (line.startsWith("#"))
        continue;
      String[] tokens = line.split("=");
      if (tokens.length == 2) {
        BufferedImage img = getImage(tokens[1].trim());
        if (img != null) {
          images.put(tokens[0].trim(), img);
        }
      }
    }
  }

  private void loadSprites() {
    sprites = new HashMap<>();
    List<String> lines = getFileLines("sprites.properties");
    for (String line : lines) {
      if (line.startsWith("#"))
        continue;
      String[] tokens = line.split(":");
      if (tokens.length == 5) {
        String codeName = tokens[0].trim();
        BufferedImage img = getImage(tokens[1].trim());
        int numberOfSequences = Integer.parseInt(tokens[2].trim());
        int numberOfFrames = Integer.parseInt(tokens[3].trim());
        boolean looped = tokens[4].trim().equals("1") ? true : false;
        if (img != null) {
          if (numberOfSequences > 0 && numberOfFrames > 0) {
            sprites.put(codeName, new Sprite(img, numberOfSequences, numberOfFrames, looped));
          } else if (numberOfFrames > 0) {
            sprites.put(codeName, new Sprite(img, numberOfFrames, looped));
          } else {
            sprites.put(codeName, new Sprite(img, numberOfSequences));
          }
        }
      }
    }
  }
}