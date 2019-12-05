package com.LP2.server.users;

import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Constants;;

public class Cook extends User {

  public Cook(String email, String pass, String name, String idCode) {
    super(email, pass, name, idCode, Constants.getCookCode());
  }

  public void closeOrder(AllOrders allOrders) {
    allOrders.remOrder();
  }

  public void checkOpenOrders(AllOrders allOrders) {
    allOrders.listOrders();
  }
}
