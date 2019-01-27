package io.tanks.common.core.utils;

import java.util.List;

public class CommonUtils {

  public static String listToString(List<Object> list) {
    return arrayToString(list.toArray(), ", ");
  }

  public static String listToString(List<Object> list, String delimiter) {
    return arrayToString(list.toArray(), delimiter);
  }

  public static String arrayToString(Object[] array) {
    return arrayToString(array, ", ");
  }

  public static String arrayToString(Object[] array, String delimiter) {
    String s = "";
    for (int i = 0; i < array.length; i++) {
      if (i < array.length - 1) {
        s += array[i] + delimiter;
      } else {
        s += array[i];
      }
    }
    return s;
  }

  public Integer tryParseInt(String s) {
    try {
      return Integer.parseInt(s);
    } catch (Exception e) {
      return null;
    }
  }

  public Double tryParseDouble(String s) {
    try {
      return Double.parseDouble(s);
    } catch (Exception e) {
      return null;
    }
  }

  public Boolean tryParseBoolean(String s) {
    try {
      return Boolean.parseBoolean(s);
    } catch (Exception e) {
      return null;
    }
  }

}
