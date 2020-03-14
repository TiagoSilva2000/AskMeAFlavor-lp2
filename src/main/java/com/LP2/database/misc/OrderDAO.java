package com.LP2.database.misc;

import java.sql.PreparedStatement;

import com.LP2.app.ErrorCodes;
import com.LP2.database.Connect;
import com.LP2.models.utils.Constants;
import com.LP2.models.utils.Order;

import org.apache.log4j.spi.ErrorCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;



public class OrderDAO {

  static public int create(final Order order) {
    try {
      int id = -1;
      ResultSet rs;
      PreparedStatement stm = Connect.getCon().prepareStatement(
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
          id = rs.getInt(6);
      }

      stm.close();
      return id;
    } catch(final Exception e) {
      e.printStackTrace();
      System.out.println(ErrorCodes.CREATIONERROR());

      return -1;
    }
  }

  static public Order read(final int id) {
    try {
      ResultSet rs = null;
      ArrayList<String> fields = new ArrayList<String>();
      PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT * FROM ClientOrder " +
        "WHERE id = (?)"
        );
      stm.setInt(1, id);
      rs = stm.executeQuery();

      if (rs != null) {
        while (rs.next()) {
          int i = 0, maxFields = rs.getMetaData().getColumnCount();
          while (i <= maxFields)
            fields.add(rs.getString(i++));
        }
      }

      rs.close();
      stm.close();
      return buildOrder(fields);
    } catch(SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  static public ArrayList<Order> all(final int status) {
    try {
      ResultSet rs = null;
      ArrayList<ArrayList<String>> fields = new ArrayList<ArrayList<String>>();
      PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT ClientOrder.quantity, ClientOrder.state, ClientOrder.item_id," +
                "ClientOrder.client_id, ClientOrder.visit_id, ClientOrder.id, " +
                "ClientOrder.ordered_at " +
        "FROM ClientOrder " +
        "WHERE state = (?) " +
        "UNION " +
        "SELECT ClientOrder.quantity, ClientOrder.state, ClientOrder.item_id, " +
                "ClientOrder.client_id, ClientOrder.visit_id, ClientOrder.id, " +
                "ClientOrder.ordered_at " +
        "FROM ClientOrder " +
        "FULL OUTER JOIN Visit ON (ClientOrder.visit_id = Visit.id) " +
        "WHERE ClientOrder.state = (?) AND Visit.exited_at IS NULL;"
        );
      stm.setInt(1, status);
      stm.setInt(2, Constants.getFinishedOrder());
      rs = stm.executeQuery();

      if (rs != null) {
        int i = 0,
            j = 1,
            maxFields = rs.getMetaData().getColumnCount();

        while (rs.next()) {
          fields.add(new ArrayList<String>());
          j = 1;
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
      PreparedStatement stm = Connect.getCon().prepareStatement(
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

  static public int update(final int visitId) {
    try {
      PreparedStatement stm = Connect.getCon().prepareStatement(
        "UPDATE ClientOrder " +
        "SET state = (?) " +
        "WHERE visit_id = (?)"
      );

      stm.setByte(1, Constants.getPaidOrder());
      stm.setInt(2, visitId);
      stm.executeUpdate();

      stm.close();
      return 1;
    } catch(SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  static public boolean delete(final int id) {
    try {
      PreparedStatement stm = Connect.getCon().prepareStatement(
        "DELETE FROM PERSON " +
        "WHERE id = (?)"
      );
      stm.setInt(1, id);
      stm.executeQuery();

      return true;
    } catch(final SQLException e) {
      e.printStackTrace();
      return false;
    }
  }


  static private Order buildOrder(final ArrayList<String> fields) {
    if (fields == null || fields.size() == 0)
      return null;
    final int qnt = Integer.parseInt(fields.get(0));
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
    Order tmp;
    for (int i = 0; i < fields.size(); i++) {
      tmp = buildOrder(fields.get(i));
      if (tmp != null)
        orders.add(tmp);
    }

    return orders;
  }
}