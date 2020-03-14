package com.LP2.database.users;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.models.users.Client;
import com.LP2.models.users.User;

public class ClientDAO {

  static public int create(final User user) {
    try {
      PreparedStatement stm = Connect.getCon().prepareStatement(
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


  static public ArrayList<String> read (final int id) {
    try {
      ResultSet result;
      ArrayList<String> fields = new ArrayList<String>();
      int i = 1, maxFields;

      PreparedStatement stm = Connect.getCon().prepareStatement(
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

  static public Client read (final int id, final boolean setter) {
    try {
      ResultSet result;
      ArrayList<String> fields = new ArrayList<String>();
      int i = 1, maxFields;

      PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT Person.id, Person.name, Person.email, Person.idCode, Person.usertype, " +
        "Person.phone, Client.lastBought, Client.lastVisit " +
        "FROM Person " +
        "INNER JOIN Client ON (Client.client_id = Person.id)" +
        "WHERE id = (?)");
      stm.setInt(1, id);
      result = stm.executeQuery();

      while (result.next()) {
        maxFields = result.getMetaData().getColumnCount();
        while (i <= maxFields) {
          System.out.println(result.getString(i));
          fields.add(result.getString(i++));
        }
      }

      stm.close();
      return buildClient(fields);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(3);
    }


    return null;
  }

  static public byte update(final Client client) {
    try {
      PreparedStatement stm = Connect.getCon().prepareStatement(
        "UPDATE Client " +
        "SET lastBought = (?), lastVisit = (?) " +
        "WHERE client_id = (?)"
      );
      stm.setDouble(1, client.getLastBought());
      stm.setDate(2, Date.valueOf(client.getLastVisit()));
      stm.setInt(3, client.getID());

      stm.executeUpdate();
      stm.close();
      return 1;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
    public static boolean remove(final int id) {
    try {
      final PreparedStatement stm = Connect.getCon().prepareStatement(
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

  static private Client buildClient(final ArrayList<String> fields) {
    final int id = Integer.parseInt(fields.get(0));
    final String name = fields.get(1);
    final String email = fields.get(2);
    final String idCode = fields.get(3);
    final byte usertype = Byte.parseByte(fields.get(4));
    final String phone = fields.get(5);
    final double lastBought = Double.parseDouble(fields.get(6));
    LocalDate date = null;
    if (fields.get(7) != null)
      date = LocalDate.parse(fields.get(7));

    return new Client(id, name, email, idCode, phone, usertype, lastBought, date);
  }

}
// PreparedStatement stm = Connect.getCon().prepareStatement(
//   "SELECT Person, Client " +
//   "FROM Person " +
//   "INNER JOIN Client " +
//   "ON Person.id = Client.client_id " +
//   "WHERE name = (?) "
// );