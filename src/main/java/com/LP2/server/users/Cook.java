package com.LP2.server.users;

import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Constants;;

public class Cook extends User {

  public Cook(String email, String pass, String name, String idCode) {
    super(email, pass, name, idCode, Constants.getCookCode());
  }

  public Cook(String username, String password) {
    super(username, password);
  }

  public Cook(final int id) { }

  public Cook(User user) {
    super(user);
  }


  public void closeOrder(AllOrders allOrders) {
    AllOrders.remOrder();
  }

  public void checkOpenOrders(AllOrders allOrders) {
    AllOrders.listOrders();
  }
}