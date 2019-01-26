package org.game.tanks.utils;

/**
 * @author rafal.kojta
 */
public class MathUtils {

  /**
   * Rounds down double number to specified decimal lenght
   * 
   * @param Double
   *          input number
   * @param digitsNumber
   *          how many decimal places
   * @return
   */
  public static Double round(Double d, double digitsNumber) {
    if (d != null) {
      double base = Math.pow(10, digitsNumber);
      int i = (int) base;
      return Math.floor(d * i) / i;
    } else {
      return null;
    }
  }

  /**
   * Computes arithmetic average of array of numbers
   * 
   * @param numberSet
   * @return Double average
   */
  public static Double average(Double[] numberSet) {
    double sum = 0;
    for (int i = 0; i < numberSet.length; i++) {
      sum += numberSet[i];
    }
    return sum / numberSet.length;
  }

  /**
   * Finds max value from array
   * 
   * @param numberSet
   * @return max value
   */
  public static Double max(Double[] numberSet) {
    if (numberSet.length != 0) {
      double max = numberSet[0];
      for (int i = 0; i < numberSet.length; i++) {
        if (max < numberSet[i]) {
          max = numberSet[i];
        }
      }
      return max;
    } else {
      return null;
    }
  }

  /**
   * Sums all numbers from array
   * 
   * @param numberSet
   * @return
   */
  public static double sum(double[] numberSet) {
    double sum = 0;
    for (int i = 0; i < numberSet.length; i++) {
      sum += numberSet[i];
    }
    return sum;
  }

  /**
   * 
   * @param numberSet
   * @param valueSet
   * @return
   */
  public static Double[] multiplyByValues(Double[] numberSet, Double[] valueSet) {
    for (int i = 0; i < numberSet.length; i++) {
      numberSet[i] = numberSet[i] * valueSet[i];
    }
    return numberSet;
  }

}