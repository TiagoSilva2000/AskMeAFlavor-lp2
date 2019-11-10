package com.LP2.utils;

// Implementar uma fila de prioridades com modificação dos indexes.
public class AllOrders {
  Order[] orders;
  byte[] availableCodes; // 1 to 100

  public AllOrders() {}

  Order pushOrder(Order newOrder) {
    // orders.push(newOrder)
    return newOrder;
  }

  public Order remOrder() {
    // orders.pop();
    return this.orders[0];
  }

  Order setPriority(byte orderCode) {
    return orders[orderCode];
  }

  Order[] getOrders() {
    return this.orders;
  }

  public void listOrders() {
    for (byte i = 0; i < orders.length; i++) {
      System.out.println(
        orders[i].getOrderString() + "Code: " + availableCodes[i]
      );
    }
  }
}
