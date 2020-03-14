package com.LP2.controllers.misc;

import java.util.ArrayList;

import com.LP2.database.misc.OrderDAO;
import com.LP2.models.items.Item;
import com.LP2.models.utils.AllOrders;
import com.LP2.models.utils.Constants;
import com.LP2.models.utils.Menu;
import com.LP2.models.utils.Order;

public class OrderController {

  static public void create(final int itemID, final int qnt) {
    Item selectedItem = Menu.selectItem(itemID);
    if (LoginController.getUser() == null || selectedItem == null)
      return;
    Order order = LoginController.getUser().order(selectedItem, qnt);
    if (order == null) {
      System.out.println("Erro...");
      System.exit(343);
    }
    AllOrders.pushOrder(order);
  }

  static public Order read(final int id, final boolean includeAll) {
    Order order = AllOrders.getOrder(id);

    if (order == null && includeAll)
      order = OrderDAO.read(id);

    return order;
  }

  static public ArrayList<Order> all(final byte state) {
    ArrayList<Order> orders = OrderDAO.all(state);

    return orders;
  }

  static public Object[][] allObj(final byte state) {
    ArrayList<Order> orders = OrderDAO.all(state);

    return allObjMatrix(orders);
  }

  static public void update(final int id, final byte state) {
    Order order = AllOrders.removeAndSetStatus(id, state);

    OrderDAO.update(order);
  }

  static public void delete(final int id) {
    OrderDAO.delete(id);
  }

  static private Object[][] allObjMatrix (final ArrayList<Order> orders) {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();

    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getStatus() == Constants.getUnOrder()) {
        objs.add(new Object[]{
          orders.get(i).getClientId(),
          orders.get(i).getID(),
          orders.get(i).getItem().getName(),
          orders.get(i).getQnt(),
        });
      }
    }
    Object[][] ob = objs.toArray(new Object[0][]);

    return ob;
  }
}