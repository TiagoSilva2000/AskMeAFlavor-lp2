package com.LP2.controllers;

import com.LP2.database.misc.OrderController;
import com.LP2.database.misc.VisitController;
import com.LP2.server.users.Client;
import com.LP2.server.users.Cook;
import com.LP2.server.users.Manager;
import com.LP2.server.users.User;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Constants;

public class LoginVV {
  private static User user;

  static public void login(final String username, final String password) {
    user = new User(username, password);
    if (user.getUsertype() == Constants.getClientCode())
      user = new Client(user);
    else if (user.getUsertype() == Constants.getManagerCode())
      user = new Manager(user);
    else
      user = new Cook(user);
  }

  static public void logout() {
    user = null;
  }

  static public double processPayment() {
    int userOrdersQnt = AllOrders.ordersQntFromUser(user.getID());
    if (userOrdersQnt == 0 && user.getOrders().size() == 0)
      return -1;

    if (userOrdersQnt > 0) {
      System.out.println("Wait until all of orders be finished please");
      return -1;
    }
    double paidValue = user.settleTheBill();
    VisitController.update(user.getVisitId(), paidValue);
    OrderController.update(user.getVisitId());

    for (int i = 0; i < user.getOrders().size(); i++) {
      user.getOrders().get(i).setStatus(Constants.getPaidOrder());
      AllOrders.removeFromList(user.getOrders().get(i).getID());
    }

    // logout();
    return paidValue;
  }

  static public User getuser() { return user; }



}