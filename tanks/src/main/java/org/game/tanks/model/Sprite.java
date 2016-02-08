package org.game.tanks.model;

import java.awt.image.BufferedImage;

/**
 * Animated Sprite. Types:
 * 1. none - rock, barrel
 * 2. animated - explosion
 * 3. animated & looped - constantly burning fire
 * 4. sequenced - nonanimated tank tower in different angles
 * 5. animated & sequenced - cannon fire animation in different angles
 * 6. animated & sequenced & looped - rolling tank wheel in different angles
 * 
 * @author Rafcio
 */
public class Sprite implements Cloneable {

  private int x;
  private int y;
  private int width;
  private int height;
  // type of sprite
  private boolean looped = false;
  private boolean animated = false;
  private boolean sequenced = false;
  // center oriented: explosions, puffs; non center oriented: tank, tower
  private boolean centered = false;// center orinted sprites image starts in the
                                   // x y center of it, non center oriented -
                                   // starts in left-top border of image

  private int currentImageIndex = 0;
  private float animationSpeed = 0.25F;
  private float animationCouner = 0;
  private int currentSequenceIndex;// sequence currently used in animation
  private BufferedImage images[][];

  /**
   * Creates sprite type 1#
   */
  public Sprite(BufferedImage image) {
    images = new BufferedImage[1][1];
    images[0][0] = image;
    width = image.getWidth();
    height = image.getHeight();
  }

  /**
   * Creates sprite type 2# or type 3# Images are stored in one big image: [] -
   * first frame [] - second frame [] - ... Here they will be splitted.
   */
  public Sprite(BufferedImage image, int numberOfFrames, boolean looped) {
    this.looped = looped;
    this.animated = true;
    this.height = image.getHeight() / numberOfFrames;
    this.width = image.getWidth();
    // images height by given desired frame height
    images = new BufferedImage[1][numberOfFrames];
    for (int i = 0; i < numberOfFrames; i++) {
      images[0][i] = image.getSubimage(0, height * i, width, height);
    }
  }

  /**
   * Creates sprite type 4# Images are stored in one big image in division of
   * sequences: [] - first sequence [] - second sequence [] - ...
   */
  public Sprite(BufferedImage image, int numberOfSequences) {
    this.height = image.getHeight() / numberOfSequences;
    this.width = image.getWidth();
    int currentImagePositionY = 0;
    images = new BufferedImage[numberOfSequences][1];
    for (int i = 0; i < numberOfSequences; i++) {
      currentImagePositionY = height * i;
      images[i][0] = image.getSubimage(0, currentImagePositionY, width, height);
    }
    this.sequenced = true;
  }

  /**
   * Creates sprite type 5# & 6# Images are placed in order: sequence1, image1,
   * image 2, ..., sequence2, image1, image2, ..., ... It looks like this: s1,
   * i11, i12, i13, s2, i21, i22, i23, s3...
   */
  public Sprite(BufferedImage image, int numberOfSequences, int framesPerSequence, boolean looped) {
    this.height = image.getHeight() / (numberOfSequences * framesPerSequence);
    this.width = image.getWidth();
    int currentImagePositionY = 0;
    images = new BufferedImage[numberOfSequences][framesPerSequence];
    for (int i = 0; i < numberOfSequences; i++) {
      for (int j = 0; j < framesPerSequence; j++) {
        System.out.println();
        images[i][j] = image.getSubimage(0, currentImagePositionY, width, height);
        currentImagePositionY += height;
      }
    }
    this.looped = looped;
    this.animated = true;
    this.sequenced = true;
  }

  public void updateTick() {
    if (animated) {
      if (currentImageIndex < images[0].length - 1) {
        animationCouner += animationSpeed;
        currentImageIndex = (int) animationCouner;
      } else if (looped) {
        currentImageIndex = 0;// back to the start
        animationCouner = 0;
      }
    }
  }
  
  /**
   * @param sequence: from 0 to maxSequenceNumber
   */
  public void setSequenceIndex(int sequence) {
    if (sequence < images.length) {
      this.currentSequenceIndex = sequence;
    } else {
      this.currentSequenceIndex = images.length - 1;// sequence finishes in table -1
    }
  }

  public boolean isFinished() {
    return (!looped && (currentImageIndex - 1 >= images[0].length));
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public BufferedImage getImage() {
    if (animated && sequenced) {
      return images[currentSequenceIndex][currentImageIndex];
    } else if (sequenced) {
      return images[currentSequenceIndex][0];
    } else if (animated) {
      return images[0][currentImageIndex];
    } else {// simple nonanimated sprite
      return images[0][0];
    }
  }

  public void setCentered(boolean centered) {
    this.centered = centered;
  }

  public void setX(int x) {
    this.x = centered ? x - width / 2 : x;
  }

  public void setY(int y) {
    this.y = centered ? y - width / 2 : y;
  }

  public void setXY(int x, int y) {
    this.x = centered ? x - width / 2 : x;
    this.y = centered ? y - height / 2 : y;
  }

}