package com.LP2.database.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.models.security.Encrypt;
import com.LP2.models.users.User;
import com.LP2.models.utils.Constants;

public class UserDAO {

  static protected boolean matchPassword(final String dbPassword,
                                        final String inPassword) {


    return dbPassword.equals(inPassword);
  }

  static public int create(final User user) {
    String salt = Encrypt.getSalt(Constants.getSaltLen());

    try {
      int id = -1;
      ResultSet result = null;
      final PreparedStatement stm = Connect.getCon()
      .prepareStatement(
        "INSERT INTO Person" +
        "(name, email, password, idcode, usertype, salt, phone)" +
        "VALUES (?,?,?,?,?, ?, ?)"
        , Statement.RETURN_GENERATED_KEYS);

      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, Encrypt.encryptPBKDF(user.getPassword(), salt));
      stm.setString(4, user.getIDCode());
      stm.setInt(5, user.getUsertype());
      stm.setString(6, salt);
      stm.setString(7, user.getPhone());
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

  static public User read(final String username, final String password) {
    ResultSet result = null;
    ArrayList<String> fields = new ArrayList<String>();
    int i = 1, maxFields;
    boolean passwordMatch = false;
    String colName;
    try {
      final PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT Person.id, Person.name, Person.email, Person.password, Person.salt, " +
        "Person.idcode, Person.usertype, Person.phone " +
        "FROM Person " +
        "WHERE name = (?);");
      stm.setString(1, username);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();
      if (result != null) {

        while (result.next()) {
          passwordMatch = Encrypt.validatePBKDF(password, result.getString("password"),
          result.getString("salt"));

          if (passwordMatch) {
            while (i <= maxFields) {
              colName = result.getMetaData().getColumnName(i);
              if (!colName.equals("password") && !colName.equals("salt"))
                fields.add(result.getString(i));
              i += 1;
            }
          } else {
            System.out.println("Authentication Failed!");
          }
        }
      }

      stm.close();
      return buildUser(fields);
    } catch (final Exception e) {
      e.printStackTrace();
      System.exit(2);
      return null;
    }
  }

  static public User read(final int id) {
    ResultSet result = null;
    ArrayList<String> fields = new ArrayList<String>();
    int i = 1, maxFields;
    boolean passwordMatch = false;
    String colName;
    try {
      final PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT Person.id, Person.name, Person.email, " +
              "Person.idcode, Person.usertype, Person.phone " +
        "FROM Person " +
        "WHERE id = (?);");
      stm.setInt(1, id);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();
      if (result != null) {
        while (result.next()) {
            while (i <= maxFields)
              fields.add(result.getString(i++));
        }
      }

      stm.close();
      return buildUser(fields);
    } catch (final Exception e) {
      e.printStackTrace();
      System.exit(2);
      return null;
    }
  }

  public static ArrayList<User> all(final byte category) {
    try {
      int i, j, maxFields;
      ResultSet rs;
      ArrayList<ArrayList<String>> fields = new ArrayList<ArrayList<String>>();
      PreparedStatement stm = Connect.getCon().prepareStatement(
        "SELECT Person.id, Person.name, Person.email, Person.idcode, " +
        "Person.usertype, Person.phone " +
        "FROM Person " +
        "WHERE usertype = (?)"
      );
      stm.setByte(1, category);
      rs = stm.executeQuery();

      i = 0;
      maxFields = rs.getMetaData().getColumnCount();
      if (rs != null) {
        while (rs.next()) {
          fields.add(new ArrayList<String>());
          j = 1;
          while (j <= maxFields) {
            fields.get(i).add(rs.getString(j));
            j++;
          }
          i++;
        }
      }

      return buildUsers(fields);
    } catch(SQLException e) {
      e.printStackTrace();
      return null;
    }

  }

  public static byte update(final User user) {
    try {
      final PreparedStatement stm = Connect.getCon().prepareStatement(
          "UPDATE Person " +
          "SET name = (?), email = (?), idcode = (?), phone = (?) " +
          "WHERE id = (?)");
      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getIDCode());
      stm.setString(4, user.getPhone());
      stm.setInt(5, user.getID());

      stm.executeUpdate();
      stm.close();
      return 0;
    } catch (final Exception e) {
      e.printStackTrace();

      return 1;
    }
  }

  public static boolean delete(final int id) {
    try {
      final PreparedStatement stm = Connect.getCon().prepareStatement(
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

  static private User buildUser(final ArrayList<String> fields) {
    if (fields.size() == 0) return null;

    final int id = Integer.parseInt(fields.get(0));
    final String name = fields.get(1);
    final String email = fields.get(2);
    final String idCode = fields.get(3);
    final byte userType = Byte.parseByte(fields.get(4));
    final String phone = fields.get(5);


    return new User(id, name, email, idCode, phone, userType);
  }

  static private ArrayList<User> buildUsers(final ArrayList<ArrayList<String>> fields) {
    ArrayList<User> users = new ArrayList<User>();

    for (int i = 0; i < fields.size(); i++) {
      User tmp = buildUser(fields.get(i));
      if (tmp != null)
        users.add(tmp);
    }
    return users;
  }
}