package com.LP2.models.users;

import com.LP2.models.utils.AllOrders;
import com.LP2.models.utils.Constants;;

public class Cook extends User {

  public Cook(String email, String pass, String name, String idCode, String phone) {
    super(name, email, pass, idCode, phone, Constants.getCookCode());
  }

  public Cook(String username, String password) {
    super(username, password);
  }

  public Cook(final int id) { this.id = id; }

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