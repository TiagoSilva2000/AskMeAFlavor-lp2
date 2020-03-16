package com.LP2.models.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Loader {
  static private Data parse (String line) {
    String[] strings = line.split("=");
    return new Data(strings[0], strings[1]);
  }

  private static void loadDataset(String variablesFilePath) {
    FileReader fr = null;

    try {
      fr = new FileReader(variablesFilePath);
    } catch (FileNotFoundException e) {
      System.out.println("The Selected file wasn't found!");
      System.exit(2);
    }

    try {
      Scanner sc = new Scanner(fr);
      while (sc.hasNextLine()) {
        Dataset.addVariable(parse(sc.nextLine()));
      }
      fr.close();
      sc.close();
    } catch(IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  static public boolean preLoad() {
    loadVariables();

    return true;
  }

  static private boolean loadVariables(String variablesFilePath) {
    loadDataset(variablesFilePath);

    return true;
  }

  static private boolean loadVariables() {
    String dir = System.getProperty("user.dir");

    loadVariables(dir + "/.env");
    return true;
  }
}