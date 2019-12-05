package com.LP2.database.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.LP2.database.Connect;
import com.LP2.server.users.Client;
import com.LP2.server.users.Cook;
import com.LP2.server.users.Manager;
import com.LP2.server.users.User;

public class UserTable {
  Connect connection;

  public UserTable(Connect connection) {
    this.connection = connection;
  }

  public boolean create(User user) {
    try {
      PreparedStatement stm = this.connection.getCon()
                              .prepareStatement("INSERT INTO Person" +
                              "(name, email, password, idcode, usertype)" +
                              "VALUES (?,?,?,?,?)"
                              );
      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getPassword());
      stm.setString(4, user.getIDCode());
      stm.setInt(5, user.getUsertype());

      stm.executeUpdate();
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
      // System.exit(1);
      return false;
    }

    return true;
  }

  public boolean get(String username, String password) {
    String hashedPass = password;
    byte i = 1;

    try {
      PreparedStatement stm = this.connection.getCon().prepareStatement(
        "SELECT * from Person" +
        " WHERE name = (?) AND password = (?);"
      );
      stm.setString(1, username);
      stm.setString(2, hashedPass);

      ResultSet result = stm.executeQuery();
      result.next();
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  public boolean update(User user) {
    try {
      PreparedStatement stm = this.connection.getCon().prepareStatement(
        "SELECT FROM person" +
        " WHERE name = (?) AND password = (?); "
      );

      stm.setString(1, user.getName());
    } catch (Exception e) {
      e.printStackTrace();

      return false;
    }

    return true;
  }

  public boolean remove(User user) { return true; }
}