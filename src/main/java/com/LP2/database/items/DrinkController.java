package com.LP2.database.items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.items.Drink;

public class DrinkController extends ItemController {

  public DrinkController(final Connect conn) {
    super(conn);
  }

  static public int create(final Drink drink) {
    try {
      final int drink_id = ItemController.create(drink);
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("INSERT INTO Drink " + "(drink_id, provider) " + "VALUES(?, ?)");
      stm.setInt(1, drink_id);
      stm.setString(2, drink.getProvider());
      stm.executeUpdate();

      stm.close();
      return drink_id;
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  static public ArrayList<String> get(final int id) {
    try {
      final ArrayList<String> fields = new ArrayList<String>();
      ResultSet result = null;
      int i = 1, maxFields;
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("SELECT * from Drink " + "WHERE drink_id = (?)");
      stm.setInt(1, id);
      result = stm.executeQuery();
      result.next();
      maxFields = result.getMetaData().getColumnCount();
      while (i <= maxFields)
        fields.add(result.getString(i++));

      stm.close();
      return fields;
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  static public boolean update(final Drink drink) {
    try {
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("UPDATE Drink " + "SET provider = (?) " + "WHERE drink_id = (?)");
      stm.setString(1, drink.getProvider());
      stm.setInt(2, drink.getID());
      stm.executeUpdate();

      stm.close();
      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  static public boolean remove(final int id) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement("DELETE FROM Drink " + "WHERE drink_id = (?)");
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