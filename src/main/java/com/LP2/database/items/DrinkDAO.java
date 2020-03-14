package com.LP2.database.items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.models.items.Drink;
import com.LP2.models.items.Item;

public class DrinkDAO {


  static public int create(final Drink drink) {
    try {
      final PreparedStatement stm = Connect.getCon()
          .prepareStatement("INSERT INTO Drink " + "(drink_id, provider) " + "VALUES(?, ?)");
      stm.setInt(1, drink.getID());
      stm.setString(2, drink.getProvider());
      stm.executeUpdate();

      stm.close();
      return drink.getID();
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  static public Drink read(final Item item) {
    try {
      final ArrayList<String> fields = new ArrayList<String>();
      ResultSet result = null;
      int i = 1, maxFields;
      final PreparedStatement stm = Connect.getCon()
          .prepareStatement("SELECT * from Drink " + "WHERE drink_id = (?)");
      stm.setInt(1, item.getID());
      result = stm.executeQuery();
      if (result != null) {
        while(result.next()) {
          maxFields = result.getMetaData().getColumnCount();
          while (i <= maxFields)
            fields.add(result.getString(i++));
        }
      }

      stm.close();
      return buildDrink(item, fields);
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  static public boolean update(final Drink drink) {
    try {
      final PreparedStatement stm = Connect.getCon()
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

  static public boolean delete (final int id) {
    try {
      final PreparedStatement stm = Connect.getCon().prepareStatement("DELETE FROM Drink " + "WHERE drink_id = (?)");
      stm.setInt(1, id);
      stm.executeUpdate();

      stm.close();
      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  static private Drink buildDrink(final Item item, final ArrayList<String> fields) {
    final String provider = fields.get(0);

    return new Drink(item, provider);
  }
}