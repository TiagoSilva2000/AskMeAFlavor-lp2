package com.LP2.users;

import com.LP2.utils.AllOrders;

public class Cook extends User {

  public Cook(String email, String pass, String name, String idCode) {
    super(email, pass, name, idCode);
  }

  void closeOrder(AllOrders allOrders) {
    allOrders.remOrder();
  }

  void checkOpenOrders(AllOrders allOrders) {
    allOrders.listOrders();
  }
}
