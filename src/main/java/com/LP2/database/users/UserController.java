package com.LP2.database.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.users.User;

public class UserController {
  static protected Connect connection;

  public UserController(Connect conn) { connection = conn; }

  static public void setConnection(final Connect conn) {
    connection = conn;
  }

  static protected boolean matchPassword(final String dbPassword,
                                        final String inPassword) {


    return dbPassword.equals(inPassword);
  }

  static public int create(final User user) {
    try {
      int id;
      ResultSet result = null;
      final PreparedStatement stm = connection.getCon()
      .prepareStatement(
        "INSERT INTO Person" +
        "(name, email, password, idcode, usertype)" +
        "VALUES (?,?,?,?,?)"
        , Statement.RETURN_GENERATED_KEYS);

      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getPassword());
      stm.setString(4, user.getIDCode());
      stm.setInt(5, user.getUsertype());

      stm.executeUpdate();
      result = stm.getGeneratedKeys();
      result.next();
      id = Integer.parseInt(result.getString(1));

      stm.close();
      return id;
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  static public ArrayList<String> get(final String username, final String password) {
    ResultSet result = null;
    ArrayList<String> fields = new ArrayList<String>();
    int i = 1, maxFields;
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "SELECT * from Person " +
        "WHERE name = (?);");
      stm.setString(1, username);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();
      result.next();
      if (matchPassword(password, result.getString("password"))) {
        while (i <= maxFields)
          fields.add(result.getString(i++));
      } else {
        System.out.println("Authentication Failed!");
      }

      stm.close();
      return fields;
    } catch (final Exception e) {
      System.out.println("Hallo!");
      e.printStackTrace();
      System.exit(2);
      return null;
    }
  }

  public static boolean update(final User user) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
          "UPDATE Person " +
          "SET name = (?), email = (?), password = (?), idcode = (?) " +
          "WHERE id = (?)");
      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getPassword());
      stm.setString(4, user.getIDCode());
      stm.setInt(5, user.getID());

      stm.executeUpdate();
      stm.close();
      return true;
    } catch (final Exception e) {
      e.printStackTrace();

      return false;
    }
  }

  public static boolean remove(final int id) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "DELETE FROM Person " +
        "WHERE id = (?)");
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