package com.LP2.app.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.LP2.app.Reader;
import com.LP2.app.Session;
import com.LP2.database.Connect;
import com.LP2.database.items.ItemController;
import com.LP2.database.misc.ImageController;
import com.LP2.database.users.UserController;

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
    loadStreams();
    loadSession();

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

  static private boolean loadStreams () {
    Reader.loadStream();
    return true;
  }

  static public boolean unloadStreams() {
    Reader.unloadStream();
    return true;
  }

  static public boolean loadSession() {
    Session.load();

    return true;
  }

  static public boolean loadControllers(Connect db) {
    UserController.setConnection(db);
    ItemController.setConnection(db);
    ImageController.setConnection(db);

    return true;
  }

}