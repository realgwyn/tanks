package org.game.tanks.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component
public class FileUtils {

  public List<String> readLinesFromResource(String resourceName) {
    List<String> result = new ArrayList<>();
    BufferedReader reader = null;
    FilenameUtils a;
    try {
      InputStream in = getClass().getResourceAsStream("/" + resourceName);
      reader = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = reader.readLine()) != null) {
        result.add(line);
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
    return result;
  }

  public Properties readPropertiesFromResource(String resourceName) {
    Properties properties = new Properties();
    InputStream stream = null;
    try {
      stream = getClass().getResourceAsStream("/" + resourceName);
      properties.load(stream);
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stream != null)
          stream.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return properties;
  }

  /**
   * loads text file lines to String array
   * 
   * @param path
   *          String - example C://file.txt
   * @return String[] fileLinesArray
   */
  public List<String> read(String path) {
    BufferedReader br = null;
    ArrayList<String> linesList = new ArrayList<String>();
    try {
      String line;
      br = new BufferedReader(new FileReader(path));

      while ((line = br.readLine()) != null) {
        linesList.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return linesList;
  }

  /**
   * writes String array to file
   * 
   * @param path
   *          String - example C://file.txt
   * @param contentArray
   *          - String []
   */
  public static void write(String path, String[] contentArray) {
    try {
      File file = new File(path);
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      for (String content : contentArray) {
        bw.write(content + "\n");

      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * appends String array to file
   * 
   * @param path
   *          String - example C://file.txt
   * @param linesArray
   *          - String []
   */
  public static void append(String path, String[] contentArray) {
    try {
      File file = new File(path);
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
      BufferedWriter bw = new BufferedWriter(fw);
      for (String content : contentArray) {
        bw.write(content + "\n");
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * deletes specified file
   * 
   * @param path
   */
  public static void delete(String path) {
    try {
      File file = new File(path);
      if (file.delete()) {
        System.out.println(file.getName() + " is deleted!");
      } else {
        System.out.println("Delete operation is failed.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Renames specified path file name to new name
   * 
   * @param oldName
   * @param newName
   */
  public static void rename(String oldName, String newName) {
    File oldfile = new File(oldName);
    File newfile = new File(newName);

    if (oldfile.renameTo(newfile)) {
      System.out.println("Rename succesful");
    } else {
      System.out.println("Rename failed");
    }
  }
}