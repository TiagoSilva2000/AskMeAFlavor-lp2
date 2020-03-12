package com.LP2.database.items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;

public class FoodController {


  static public int create(final Food food) {
    try {
      final PreparedStatement stm = Connect.getCon()
          .prepareStatement("INSERT INTO Food " + "(food_id, description)" + " VALUES (?, ?)");
      stm.setInt(1, food.getID());
      stm.setString(2, food.getDesc());
      stm.executeUpdate();

      stm.close();
      return food.getID();
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }

  }

  static public Food read(final Item item) {
    try {
      final ArrayList<String> fields = new ArrayList<String>();
      ResultSet result = null;
      int i = 1, maxFields;
      final PreparedStatement stm = Connect.getCon()
        .prepareStatement("SELECT * FROM Food " + "WHERE food_id = (?)");
      stm.setInt(1, item.getID());
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();
      if (result != null)
        while (result.next())
          while (i <= maxFields)
            fields.add(result.getString(i++));

      stm.close();
      return buildFood(item, fields);
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  static public boolean update(final Food food) {
    try {
      final PreparedStatement stm = Connect.getCon()
          .prepareStatement("UPDATE Food " + "SET description = (?) " + "WHERE food_id = (?)");
      stm.setString(1, food.getDesc());
      stm.setInt(2, food.getID());
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
      final PreparedStatement stm = Connect.getCon().prepareStatement("DELETE FROM Food " + "WHERE food_id = (?)");
      stm.setInt(1, id);
      stm.executeUpdate();

      stm.close();
      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  static private Food buildFood(final Item item, final ArrayList<String> fields) {
    final String description = fields.get(0);

    return new Food(item, description);
  }

}