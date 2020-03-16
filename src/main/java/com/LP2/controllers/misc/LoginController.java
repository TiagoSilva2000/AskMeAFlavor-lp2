package com.LP2.controllers.misc;

import com.LP2.database.misc.OrderDAO;
import com.LP2.database.misc.VisitDAO;
import com.LP2.models.security.Auth;
import com.LP2.models.users.Client;
import com.LP2.models.users.Cook;
import com.LP2.models.users.Manager;
import com.LP2.models.users.User;
import com.LP2.models.utils.AllOrders;
import com.LP2.models.utils.Constants;
import com.LP2.models.utils.Order;

public class LoginController {

  static public int login(final String username, final String password) {
    User user = new User(username, password);

    System.out.println(user.getID());
    if (user.getID() == -1)
      return 0;

    if (user.getUsertype() == Constants.getClientCode())
      user = new Client(user);
    else if (user.getUsertype() == Constants.getManagerCode())
      user = new Manager(user);
    else
      user = new Cook(user);
    Auth.setUser(user);
    return 1;
  }

  static public void logout() {
    Auth.setUser(null);
  }

  static public double processPayment() {
    int userOrdersQnt = AllOrders.ordersQntFromUser(Auth.getUser().getID());
    if (userOrdersQnt == 0 && Auth.getUser().getOrders().size() == 0)
      return -1;

    if (userOrdersQnt > 0) {
      return -1;
    }
    double paidValue = Auth.getUser().settleTheBill();
    VisitDAO.update(Auth.getUser().getVisitId(), paidValue);
    OrderDAO.update(Auth.getUser().getVisitId());

    for (Order order : Auth.getUser().getOrders()) {
      order.setStatus(Constants.getPaidOrder());
      AllOrders.removeAndSetStatus(order.getID(), Constants.getPaidOrder());
    }

    return paidValue;
  }

  static public User getUser() { return Auth.getUser(); }

}