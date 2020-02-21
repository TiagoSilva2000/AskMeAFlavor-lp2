package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
// import com.LP2.view.terminal.routes.Router;

public class App {

  public static void main(String[] args) {
    Connect db = null;
    // Router router = null;

    // adicionar um loader de menu e all orders
    Loader.preLoad();
    db = new Connect();
    // router = new Router();
    Loader.loadControllers(db);

    // router.initialize();

    Loader.unloadStreams();
  }
}