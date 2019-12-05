package com.LP2.app.loader;

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
    }

  }

  static public boolean load(String variablesFilePath) {
    loadDataset(variablesFilePath);

    return true;
  }

  static public boolean load() {
    String dir = System.getProperty("user.dir");

    load(dir + "/.env");
    return true;
  }
}