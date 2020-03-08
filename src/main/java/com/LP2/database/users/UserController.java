package com.LP2.database.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.security.Encrypt;
import com.LP2.server.users.User;
import com.LP2.server.utils.Constants;

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
    String salt = Encrypt.getSalt(Constants.getSaltLen());

    try {
      int id = -1;
      ResultSet result = null;
      final PreparedStatement stm = connection.getCon()
      .prepareStatement(
        "INSERT INTO Person" +
        "(name, email, password, idcode, usertype, salt)" +
        "VALUES (?,?,?,?,?, ?)"
        , Statement.RETURN_GENERATED_KEYS);

      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, Encrypt.encryptPBKDF(user.getPassword(), salt));
      stm.setString(4, user.getIDCode());
      stm.setInt(5, user.getUsertype());
      stm.setString(6, salt);
      stm.executeUpdate();
      result = stm.getGeneratedKeys();
      while (result.next())
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
    boolean passwordMatch = false;
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "SELECT * from Person " +
        "WHERE name = (?);");
      stm.setString(1, username);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();
      while (result.next()) {
        passwordMatch = Encrypt.validatePBKDF(password, result.getString("password"),
        result.getString("salt"));
        if (passwordMatch) {
          while (i <= maxFields)
          fields.add(result.getString(i++));
        } else {
          System.out.println("Authentication Failed!");
        }
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