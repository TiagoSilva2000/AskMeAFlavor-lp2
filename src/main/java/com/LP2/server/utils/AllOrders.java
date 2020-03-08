package com.LP2.server.utils;

import java.util.ArrayList;

import com.LP2.database.misc.OrderController;

// Implementar uma fila de prioridades com modificação dos indexes.
public class AllOrders {
  static private ArrayList<Order> orders;
  static private ArrayList<Byte> availableCodes; // 1 to 100
  static private byte nextCode;

  static public void load(final int status) {
    orders = OrderController.all(status);
    availableCodes = new ArrayList<Byte>();
  }

  static public Order pushOrder(Order newOrder) {
    orders.add(newOrder);
    availableCodes.add(nextCode);
    nextCode++;
    return newOrder;
  }

  static public Order remOrder() {
    return orders.remove(0);
  }

  static public Order remOrder(final int id) {
    Order tmp = null;
    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getID() == id) {
        tmp = orders.get(i);
        orders.remove(tmp);
      }
    }

    tmp.setStatus(Constants.getFinishedOrder());
    OrderController.update(tmp);
    return tmp;
  }

  static public Order setPriority(byte orderCode) {
    return orders.get(orderCode);
  }

  static public ArrayList<Order> getOrders() {
    return orders;
  }

  static public void listOrders() {
    for (byte i = 0; i < orders.size(); i++) {
      System.out.println(
        orders.get(i).getOrderString() + " - Code: " + orders.get(i).getID()
      );
    }
  }
}
