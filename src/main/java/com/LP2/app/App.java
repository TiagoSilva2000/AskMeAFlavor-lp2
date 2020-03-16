package com.LP2.app;

import com.LP2.models.loader.Loader;
import com.LP2.database.Connect;
import com.LP2.models.utils.AllOrders;
import com.LP2.models.utils.Constants;
import com.LP2.models.utils.Menu;
import com.LP2.view.pages.Login;

public class App {

  public static void main(String[] args) {

    Loader.preLoad();

    Connect.connect();
    Menu.load(Constants.getPresent());
    AllOrders.load(Constants.getUnOrder());

    Login.main(args);
    // Connect.close();
  }
}