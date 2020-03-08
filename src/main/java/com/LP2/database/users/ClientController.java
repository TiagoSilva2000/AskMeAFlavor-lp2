package com.LP2.database.users;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.users.Client;
import com.LP2.server.users.User;

public class ClientController extends UserController{

  public ClientController(Connect conn) {
    super(conn);
  }

  static public int create(final User user) {
    try {
      PreparedStatement stm = connection.getCon().prepareStatement(
          "INSERT INTO Client" + "(client_id, lastBought, lastVisit)" +
          "VALUES(?, 0, NULL)"
      );
      stm.setInt(1, user.getID());
      stm.executeUpdate(); stm.close();

      return user.getID();
    } catch (Exception e) {
      e.printStackTrace();

      return -1;
    }
  }


  static public ArrayList<String> get (final int id) {
    try {
      ResultSet result;
      ArrayList<String> fields = new ArrayList<String>();
      int i = 1, maxFields;

      PreparedStatement stm = connection.getCon().prepareStatement(
        "SELECT * FROM Client " +
        "WHERE client_id = (?)");
      stm.setInt(1, id);
      result = stm.executeQuery();

      while (result.next()) {
        maxFields = result.getMetaData().getColumnCount();
        while (i <= maxFields)
          fields.add(result.getString(i++));
      }

      stm.close();
      return fields;
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(3);
    }


    return null;
  }

  static public void update(final Client client) {
    try {
      PreparedStatement stm = connection.getCon().prepareStatement(
        "UPDATE Client " +
        "SET lastBought = (?), lastVisit = (?) " +
        "WHERE client_id = (?)"
      );
      stm.setDouble(1, client.getLastBought());
      stm.setDate(2, Date.valueOf(client.getLastVisit()));
      stm.setInt(3, client.getID());

      stm.executeUpdate();
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    public static boolean remove(final int id) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "DELETE FROM Client " +
        "WHERE client_id = (?)");
      stm.setInt(1, id);

      stm.executeUpdate();
      stm.close();

      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

}
// PreparedStatement stm = connection.getCon().prepareStatement(
//   "SELECT Person, Client " +
//   "FROM Person " +
//   "INNER JOIN Client " +
//   "ON Person.id = Client.client_id " +
//   "WHERE name = (?) "
// );