package com.LP2.controllers;

import java.util.ArrayList;

import com.LP2.database.misc.OrderController;
import com.LP2.server.items.Item;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Menu;
import com.LP2.server.utils.Order;

public class OrderVV {

  static public void create(final int itemID, final int qnt) {
    Item selectedItem = Menu.selectItem(itemID);
    if (LoginVV.getuser() == null || selectedItem == null)
      return;
    Order order = LoginVV.getuser().order(selectedItem, qnt);
    if (order == null) {
      System.out.println("Erro...");
      System.exit(343);
    }
    AllOrders.pushOrder(order);
  }

  static public void read() {}

  static private Object[][] allObjMatrix (final ArrayList<Order> orders) {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();

    for (int i = 0; i < orders.size(); i++) {
      objs.add(new Object[]{
        orders.get(i).getClientId(),
        orders.get(i).getID(),
        orders.get(i).getItem().getName(),
        orders.get(i).getQnt(),
      });
    }
    Object[][] ob = objs.toArray(new Object[0][]);
    System.out.println(ob.length);

    return ob;
  }

  static public ArrayList<Order> all(final byte state) {
    ArrayList<Order> orders = OrderController.all(state);

    return orders;
  }

  static public Object[][] allObj(final byte state) {
    ArrayList<Order> orders = OrderController.all(state);

    return allObjMatrix(orders);
  }

  static public void update() {}

  static public void delete() {}

}