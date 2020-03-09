package com.LP2.database.items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;
import com.LP2.server.resources.Image;
import com.LP2.server.utils.Constants;

public class ItemController {
  static protected Connect connection;

  public ItemController(final Connect conn) {
    connection = conn;
  }

  static public void setConnection(final Connect conn) {
    connection = conn;
  }

  static public int create(final Item item) {


    try {
      ResultSet result;
      int id = -1;
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("INSERT INTO Item" +
                            "(name, price, img_id, present) " +
                            "VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, item.getName());
      stm.setDouble(2, item.getPrice());
      if (item.getImage() != null)
        stm.setInt(3, item.getImage().getID());
      else
        stm.setNull(3, Types.INTEGER);
      stm.setBoolean(4, item.isPresent());
      stm.executeUpdate();
      result = stm.getGeneratedKeys();
      while (result.next())
        id = result.getInt(1);

      stm.close();
      return id;
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }


  static public ArrayList<ArrayList<String>> all (final byte presenceCode) {
    try {
      final ArrayList<ArrayList<String>> fields = new ArrayList<ArrayList<String>>();
      ResultSet result = null;
      int i, j, maxFields;
      String queryString = (
      "SELECT " +
        "Item.id, Item.name, Item.price, Item.img_id, " +
        "Drink.provider, Food.description, Image.filepath, " +
        "Image.filename, Image.filetype, Image.content, " +
          "CASE WHEN Drink.provider IS NOT NULL THEN 'drink' " +
          "ELSE 'food' " +
          "END AS itemType " +
        "FROM Item " +
      "FULL OUTER JOIN Food ON (Item.id = Food.food_id) " +
      "FULL OUTER JOIN Drink ON (Drink.drink_id = Item.id) " +
      "FULL OUTER JOIN Image ON (Item.img_id = Image.id) ");

      if (presenceCode == Constants.getPresent())
        queryString += "WHERE Item.present = true";
      else if (presenceCode == Constants.getNotPresent())
        queryString += "WHERE Item.present = false";

      final PreparedStatement stm = connection.getCon()
                                    .prepareStatement(queryString);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();

      i = 0;
      j = 1;
      if (result != null) {
        while (result.next()) {
          j = 1;
          fields.add(new ArrayList<String>());
          while (j <= maxFields) {
            String field = result.getMetaData().getColumnName(j);
            String content = result.getString(j++);

            if (content == null)
              content = "null";

            if ((field.equals("img_id") || !content.equals("null")))
              fields.get(i).add(content);
          }
          i += 1;
        }
      }

      result.close();
      stm.close();
      return fields;
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  static private Item buildItem (final ArrayList<String> fields) {
    if (fields.size() == 0)
      return null;

    final int id = Integer.parseInt(fields.get(0));
    final String name = fields.get(1);
    final double price = Double.parseDouble(fields.get(2));
    final boolean present = Boolean.parseBoolean(fields.get(3));
    Image img = null;

    if (fields.get(4) != null)
      img = new Image(Integer.parseInt(fields.get(4)));

    if (fields.get(5) == null) {
      return new Food(id, name, price, fields.get(6), null);
    } else {
      return new Food(id, name, price, fields.get(5), null);
    }

    // return new Item(id, name, price, present, img);
  }

  static public Item getItem(final int id) {
    System.out.println(id);
    try {
      final ArrayList<String> fields = new ArrayList<String>();
      ResultSet result = null;
      int i, maxFields = 0;
      final PreparedStatement stm = connection.getCon()
        .prepareStatement("SELECT " +
                            "Item.id, Item.name, Item.price, " +
                            "Item.present, Item.img_id, Food.description " +
                          "FROM Item " +
                          "INNER JOIN Food ON (Food.food_id = Item.id) " +
                          "WHERE id = (?) " +
                          "UNION " +
                          "SELECT " +
                            "Item.id, Item.name, Item.price, " +
                            "Item.present, Item.img_id, Drink.provider " +
                          "FROM Item " +
                          "INNER JOIN Drink ON (Drink.drink_id = Item.id) " +
                          "WHERE id = (?)"
                          );
      stm.setInt(1, id);
      stm.setInt(2, id);
      result = stm.executeQuery();

      i = 1;
      while (result.next()) {
        maxFields = result.getMetaData().getColumnCount();
        i = 1;
        while (i <= maxFields) {
          fields.add(result.getString(i));
          // System.out.println(fields.get(i-1));
          i++;
        }
      }

      stm.close();
      return buildItem(fields);
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  static public boolean update(final Item item) {
    try {
      boolean isPresent = item.isPresent();
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("UPDATE Item " +
                              "SET name = (?), price = (?), " +
                              "present = (?), img_id = (?) " +
                            "WHERE id = (?)");
      stm.setString(1, item.getName());
      stm.setDouble(2, item.getPrice());
      stm.setBoolean(3, isPresent);
      if (item.getImage() == null) {
        stm.setNull(4, Types.INTEGER);
      } else {
        stm.setInt(4, item.getImage().getID());
      }
      stm.setInt(5, item.getID());
      stm.executeUpdate();

      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  static public boolean remove(final int id) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement("DELETE FROM Item " + "WHERE id = (?)");
      stm.setInt(1, id);
      stm.executeUpdate();

      stm.close();
      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  static public boolean remove(final String name) {
    try {
      final PreparedStatement stm = connection.getCon().prepareStatement(
        "DELETE FROM Item " +
        "WHERE name = (?)");
      stm.setString(1, name);
      stm.executeUpdate();

      stm.close();
      return true;
    } catch (final Exception e) {
      e.printStackTrace();
      return false;
    }
  }

}