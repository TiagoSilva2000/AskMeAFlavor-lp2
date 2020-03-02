package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
import com.LP2.server.security.Encrypt;
import com.LP2.server.users.Client;
import com.LP2.server.users.User;
import com.LP2.server.validators.Validator;

public class App {

  public static void main(String[] args) {
    Connect db = null;
    Client client = null;

    Loader.preLoad();
    db = new Connect();
    Loader.loadControllers(db);

    Loader.unloadStreams();
  }
}