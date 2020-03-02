package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
import com.LP2.view.pages.Login;

public class App {

  public static void main(String[] args) {
    Connect db = null;
    Router router = null;


    Loader.preLoad();
    db = new Connect();
    Loader.loadControllers(db);

    Login.main(args);
    // db.createOrderTable();
    // db.deleteTable("clientorder");
    // router = new Router(db);
    // Login.main(args);

    Loader.unloadStreams();
  }
}