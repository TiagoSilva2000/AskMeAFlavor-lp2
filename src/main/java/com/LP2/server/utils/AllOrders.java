package com.LP2.server.utils;

import java.util.ArrayList;

// Implementar uma fila de prioridades com modificação dos indexes.
public class AllOrders {
  static private ArrayList<Order> orders;
  static private ArrayList<Byte> availableCodes; // 1 to 100
  static private byte nextCode;

  public AllOrders() {
    orders = new ArrayList<Order>();
    availableCodes = new ArrayList<Byte>();
    nextCode = 0;
  }

  static public void load() {
    orders = new ArrayList<Order>();
    availableCodes = new ArrayList<Byte>();
    nextCode = 0;
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

  static public Order setPriority(byte orderCode) {
    return orders.get(orderCode);
  }

  static public ArrayList<Order> getOrders() {
    return orders;
  }

  static public void listOrders() {
    for (byte i = 0; i < orders.size(); i++) {
      System.out.println(
        orders.get(i).getOrderString() + " - Code: " + availableCodes.get(i)
      );
    }
  }
}
