package com.LP2.models.utils;

import java.util.ArrayList;

import com.LP2.database.misc.OrderDAO;

// Implementar uma fila de prioridades com modificação dos indexes.
public class AllOrders {
  static private ArrayList<Order> orders;
  static private ArrayList<Byte> availableCodes; // 1 to 100
  static private byte nextCode;

  static public void load(final int status) {
    orders = OrderDAO.all(status);
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

  static public Order removeAndSetStatus(final int id, final byte status) {
    Order tmp = null;
    int i = 0;
    if (status < Constants.getCanceledOrder() || status > Constants.getPaidOrder())
      return tmp;

    while (i < orders.size()) {
      if (orders.get(i).getID() == id) {
        orders.get(i).setStatus(status);
        tmp = orders.get(i);
        break;
      }
      i++;
    }

    return tmp;
  }

  static public int ordersQntFromUser(final int id) {
    int counter = 0;
    for (int i = 0; i < orders.size(); i++)
      if (orders.get(i).getClientId() == id && orders.get(i).getStatus() == Constants.getUnOrder())
        counter++;

    return counter;
  }

  static public ArrayList<Order> ordersFromUser(final int id) {
    ArrayList<Order> userOrders = new ArrayList<Order>();

    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getClientId() == id)
        userOrders.add(orders.get(i));
    }

    return userOrders;
  }

  static public Order getOrder (final int id) {

    for (Order order : orders)
      if (order.getID() == id)
        return order;

    return null;
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
        orders.get(i).getOrderString() + " - Code: " + orders.get(i).getID() +
        " - ClientID: " + orders.get(i).getClientId()
      );
    }
  }
}
