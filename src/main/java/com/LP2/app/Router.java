package com.LP2.app;

import com.LP2.database.Connect;
import com.LP2.server.users.User;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Menu;

final public class Router {
  private User loggedUser;
  private Menu menu;
  private AllOrders currentOrders;
  private Connect db;

  public Router(final Connect db) {
    this.db = db;
    this.loggedUser = null;
    this.menu = new Menu();
    this.currentOrders = new AllOrders();
  }
}