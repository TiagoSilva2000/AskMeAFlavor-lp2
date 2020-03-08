package com.LP2.database.misc;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.LP2.app.ErrorCodes;
import com.LP2.database.Connect;

public class VisitController {
  static private Connect conn;


  public VisitController(final Connect connection) {
    conn = connection;
  }

  static public void setConnection(final Connect connection) {
    conn = connection;
  }



  static public int create(final int clientID) {
    try {
      int id = -1;
      ResultSet rs;
      PreparedStatement stm = conn.getCon().prepareStatement(
        "INSERT INTO Visit " +
        "(entered_at, exited_at, total_spent, client_id) " +
        "VALUES (?, ?, ?, ?)"
      , Statement.RETURN_GENERATED_KEYS);
      stm.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
      // stm.setNull(2, java.sql.Types.TIMESTAMP);
      stm.setTimestamp(2, null);
      stm.setDouble(3, 0);
      stm.setInt(4, clientID);
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


  static public int getOpenVisit(final int clientID) {
    int openId = -1;
    try {
      ResultSet rs;
      PreparedStatement stm = conn.getCon().prepareStatement(
        "SELECT * FROM Visit " +
        "WHERE client_id = (?)"
      );
      stm.setInt(1, clientID);
      rs = stm.executeQuery();

      if (rs != null)
        while(rs.next())
          openId = Integer.parseInt(rs.getString("id"));

      return openId;
    } catch(SQLException e) {
      e.printStackTrace();
      return openId;
    }

  }

  static public void update(final int visitId, final double totalSpent) {
    try {
      PreparedStatement stm = conn.getCon().prepareStatement(
        "UPDATE Visit " +
        "SET exited_at = (?), total_spent = (?) " +
        "WHERE id = (?)"
      );
      stm.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
      stm.setDouble(2, totalSpent);
      stm.setInt(3, visitId);
      stm.executeUpdate();
      stm.close();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }


}