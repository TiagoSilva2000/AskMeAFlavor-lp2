package com.LP2.utils;

import java.util.ArrayList;

// Implementar uma fila de prioridades com modificação dos indexes.
public class AllOrders {
  ArrayList<Order> orders;
  ArrayList<Integer> availableCodes; // 1 to 100
  int nextCode;

  public AllOrders() {
    this.orders = new ArrayList<Order>();
    this.availableCodes = new ArrayList<Integer>();
    nextCode = 0;
  }

  public Order pushOrder(Order newOrder) {
    orders.add(newOrder);
    availableCodes.add(this.nextCode);
    nextCode++;
    return newOrder;
  }

  public Order remOrder() {
    return orders.remove(0);
  }

  Order setPriority(byte orderCode) {
    return orders.get(orderCode);
  }

  ArrayList<Order> getOrders() {
    return this.orders;
  }

  public void listOrders() {
    for (byte i = 0; i < orders.size(); i++) {
      System.out.println(
        orders.get(i).getOrderString() + " - Code: " + availableCodes.get(i)
      );
    }
  }
}
