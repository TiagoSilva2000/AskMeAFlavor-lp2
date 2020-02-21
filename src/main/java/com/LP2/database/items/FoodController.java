package com.LP2.database.items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.items.Food;

public class FoodController extends ItemController {

  public FoodController(final Connect conn) {
    super(conn);
  }

  static public int create(final Food food) {
    try {
      final int id = ItemController.create(food);
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("INSERT INTO Food " + "(food_id, description)" + "(?, ?)");
      stm.setInt(1, food.getID());
      stm.setString(2, food.getDesc());
      stm.executeUpdate();

      stm.close();
      return id;
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
      final PreparedStatement stm = connection.getCon().prepareStatement("SELECT * FROM Food " + "WHERE food_id = (?)");
      stm.setInt(1, id);
      result = stm.executeQuery();
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

  static public boolean update(final Food food) {
    try {
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("UPDATE Food " + "SET descritpion = (?) " + "WHERE food_id = (?)");
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
      final PreparedStatement stm = connection.getCon().prepareStatement("DELETE FROM Food " + "WHERE food_id = (?)");
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