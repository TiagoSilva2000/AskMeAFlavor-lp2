package com.LP2.database.misc;

import java.sql.PreparedStatement;

import com.LP2.app.ErrorCodes;
import com.LP2.database.Connect;
import com.LP2.server.utils.Order;

import org.apache.log4j.spi.ErrorCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class OrderController {
  static protected Connect conn;

  public OrderController(final Connect connection) {
    conn = connection;
  }

  static public void setConnection(final Connect connection) {
    conn = connection;
  }

  static public int create(final Order order, final int client_id) {
    try {
      int id;
      ResultSet rs;
      PreparedStatement stm = conn.getCon().prepareStatement(
        "INSERT INTO ClientOrder " +
        "(client_id, item_id, quantity, state) " +
        "VALUES (?, ?, ?, ?)"
      );
      stm.setInt(1, client_id);
      stm.setInt(2, order.getItem().getID());
      stm.setInt(3, order.getQnt());
      stm.setInt(4, order.getStatus());
      stm.executeUpdate();
      rs = stm.getGeneratedKeys();
      rs.next();
      id = rs.getInt(1);

      stm.close();
      return id;
    } catch(final Exception e) {
      e.printStackTrace();
      System.out.println(ErrorCodes.CREATIONERROR());

      return -1;
    }
  }

  static public int update(final Order order) {


    return 1;
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