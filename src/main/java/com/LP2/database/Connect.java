package com.LP2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.sql.Statement;

import com.LP2.users.User;

public class Connect {
  private String url;
  private String username;
  private String password;
  private Connection conn;

  public Connect() {
    this.url = "jdbc:postgresql://localhost:5432/askMeAFlavor";
    this.username = "postgres";
    this.password = "PENSOU QUE IA PEGAR A MINHA SENHA, NÃO É MESMO?";

    try {
      Class.forName("org.postgresql.Driver");
      this.conn =
        DriverManager.getConnection(this.url, this.username, this.password);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Conexão sucedida");
  }

  public boolean createUserTable() {
    try  {
      Statement stm = this.conn.createStatement();
      stm.executeQuery("CREATE TABLE Person (" +
                        "id SERIAL NOT NULL PRIMARY KEY," +
                        "name varchar(80)," +
                        "email varchar(80)," +
                        "password varchar(80)," +
                        "IDcode varchar(80)," +
                        "userType smallint" +
                        ");");
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean insertUserTable(User user) {
    try {
      PreparedStatement stm = this.conn.prepareStatement("INSERT INTO Person" +
                              "(name, email, password, idcode, usertype)" +
                              "VALUES (?,?,?,?,?)"
                              );
      stm.setString(1, user.getName());
      stm.setString(2, user.getEmail());
      stm.setString(3, user.getPassword());
      stm.setString(4, user.getIDCode());
      stm.setInt(5, 1);
      stm.executeUpdate();
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }

  public boolean createItemTable() {
    try {
      Statement stm = this.conn.createStatement();
      stm.executeQuery("CREATE TABLE Item (" +
                        "id SERIAL NOT NULL PRIMARY KEY," +
                        "name varchar(100)," +
                        "price real," +
                        "isFood bool," +
                        "isDrink bool" +
                      ");");

      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }


    return true;
  }

  public boolean createFoodTable () {
    try {
      Statement stm = this.conn.createStatement();
      stm.executeQuery("CREATE TABLE Food (" +
                        "food_id integer NOT NULL," + // colocar como foreign key de item.
                        "description varchar(240)," +
                        "PRIMARY KEY(food_id)," +
                        "FOREIGN KEY(food_id) REFERENCES Item(id)" +
                      ")");
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }

  public boolean createDrinkTable() {
    try {
      Statement stm = this.conn.createStatement();
      stm.executeQuery("CREATE TABLE Drink (" +
                        "drink_id integer NOT NULL," + // colocar como foreign key de item.
                        "provider varchar(80)," +
                        "PRIMARY KEY(drink_id)," +
                        "FOREIGN KEY(drink_id) REFERENCES Item(id)" +
                      ")");
      stm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }

  public boolean deleteTable(String tableName) {
    try {
      Statement stm = this.conn.createStatement();
      stm.executeQuery("DROP TABLE " + tableName);

      stm.close();
    } catch (Exception e) {
      e.printStackTrace();

      return false;
    }

    return true;
  }

}
