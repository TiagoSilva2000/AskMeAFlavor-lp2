package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;

public class App {

  public static void printIt() {
    System.out.println("Hello, World!");
  }


  public static void main(String[] args) {
    // Connect db = null;

    printIt();
    // Loader.preLoad();
    // db = new Connect();
    // Loader.loadControllers(db);


    // Loader.unloadStreams();
  }
}