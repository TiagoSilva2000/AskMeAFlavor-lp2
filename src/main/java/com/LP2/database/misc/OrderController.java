package com.LP2.database.misc;

import java.sql.PreparedStatement;

import com.LP2.app.ErrorCodes;
import com.LP2.database.Connect;
import com.LP2.server.utils.Order;

import org.apache.log4j.spi.ErrorCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;



public class OrderController {
  static protected Connect conn;

  public OrderController(final Connect connection) {
    conn = connection;
  }

  static public void setConnection(final Connect connection) {
    conn = connection;
  }

  static public int create(final Order order) {
    try {
      int id = -1;
      ResultSet rs;
      PreparedStatement stm = conn.getCon().prepareStatement(
        "INSERT INTO ClientOrder " +
        "(quantity, state, item_id, client_id, visit_id) " +
        "VALUES (?, ?, ?, ?, ?)"
      , Statement.RETURN_GENERATED_KEYS);
      stm.setInt(1, order.getQnt());
      stm.setInt(2, order.getStatus());
      stm.setInt(3, order.getItem().getID());
      stm.setInt(4, order.getClientId());
      stm.setInt(5, order.getVisitId());
      stm.executeUpdate();
      rs = stm.getGeneratedKeys();

      if (rs != null) {
        while (rs.next())
          id = rs.getInt(1);
      }

      stm.close();
      return id;
    } catch(final Exception e) {
      e.printStackTrace();
      System.out.println(ErrorCodes.CREATIONERROR());

      return -1;
    }
  }

  static private Order buildOrder(final ArrayList<String> fields) {
    final int qnt = Integer.parseInt(fields.get(0));
    // System.out.println(fields.get(1));
    final byte status = Byte.parseByte(fields.get(1));
    final int itemId = Integer.parseInt(fields.get(2));
    final int clientId = Integer.parseInt(fields.get(3));
    final int visitId = Integer.parseInt(fields.get(4));
    final int id = Integer.parseInt(fields.get(5));
    final Timestamp ordered_at = Timestamp.valueOf(fields.get(6));

    return new Order(id, visitId, clientId, itemId, status, ordered_at, qnt);
  }

  static private ArrayList<Order> buildOrders (final ArrayList<ArrayList<String>> fields) {
    ArrayList<Order> orders = new ArrayList<Order>();

    for (int i = 0; i < fields.size(); i++) {
      orders.add(buildOrder(fields.get(i)));
    }

    return orders;
  }

  static public ArrayList<Order> all(final int status) {
    try {
      ResultSet rs = null;
      ArrayList<ArrayList<String>> fields = new ArrayList<ArrayList<String>>();
      PreparedStatement stm = conn.getCon().prepareStatement(
        "SELECT * FROM ClientOrder " +
        "WHERE state = (?)"
        );
      stm.setInt(1, status);
      rs = stm.executeQuery();

      if (rs != null) {
        int i = 0,
            j = 1,
            maxFields = rs.getMetaData().getColumnCount();

        while (rs.next()) {
          fields.add(new ArrayList<String>());
          while (j <= maxFields)
            fields.get(i).add(rs.getString(j++));
          i += 1;
        }
      }

      rs.close();
      stm.close();
      return buildOrders(fields);
    } catch(SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  static public int update(final Order order) {
    try {
      PreparedStatement stm = conn.getCon().prepareStatement(
        "UPDATE ClientOrder " +
        "SET state = (?) " +
        "WHERE id = (?)"
      );
      stm.setByte(1, order.getStatus());
      stm.setInt(2, order.getID());
      stm.executeUpdate();

      stm.close();
      return 1;
    } catch(SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  static public ArrayList<ArrayList<String>> readByStatus(byte status) {
    if (status < -1 || status > 1)
      return null;
    try {
      ArrayList<ArrayList<String>> fields = new ArrayList<ArrayList<String>>();
      ResultSet rs;
      int i, j, maxFields;
      PreparedStatement stm = conn.getCon().prepareStatement(
        "SELECT * FROM ClientOrder WHERE status = (?)"
      );
      stm.setInt(1, status);
      rs = stm.executeQuery();


      i = 0;
      j = 1;
      maxFields = rs.getMetaData().getColumnCount();
      while (rs.next()) {
        j = 1;
        fields.add(new ArrayList<String>());
        while (j <= maxFields)
          fields.get(i).add(rs.getString(j++));
        i += 1;
      }
      stm.close();
      return fields;
    } catch(final SQLException e) {
      e.printStackTrace();
      System.out.println(ErrorCodes.READERROR());

      return null;
    }


  }
}