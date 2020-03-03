package com.LP2.database.items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.server.items.Item;

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
      int id;
      final PreparedStatement stm = connection.getCon()
          .prepareStatement("INSERT INTO Item" +
                            "(name, price, img_id) " +
                            "VALUES (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
      stm.setString(1, item.getName());
      stm.setDouble(2, item.getPrice());
      if (item.getImage() != null)
        stm.setInt(3, item.getImage().getID());
      else
        stm.setNull(3, Types.INTEGER);

      stm.executeUpdate();
      result = stm.getGeneratedKeys();
      result.next();
      id = result.getInt(1);

      stm.close();
      return id;
    } catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  static public ArrayList<ArrayList<String>> all () {
    try {
      final ArrayList<ArrayList<String>> fields = new ArrayList<ArrayList<String>>();
      ResultSet result = null;
      int i, j, maxFields;
      final PreparedStatement stm = connection.getCon()
        .prepareStatement("SELECT " +
                            "Item.id, Item.name, Item.price, Item.img_id, " +
                            "Drink.provider, Food.description, Image.filepath, " +
                            "Image.filename, Image.filetype, Image.content, " +
                            "CASE WHEN Drink.provider IS NOT NULL THEN 'drink' " +
                            "ELSE 'food' " +
                            "END AS itemType " +
                            "FROM Item " +
                          "FULL OUTER JOIN Food ON (Item.id = Food.food_id) " +
                          "FULL OUTER JOIN Drink ON (Drink.drink_id = Item.id) " +
                          "FULL OUTER JOIN Image ON (Item.img_id = Image.id)" );
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();

      i = 0;
      j = 1;
      while (result.next()) {
        j = 1;
        fields.add(new ArrayList<String>());
        while (j <= maxFields) {
          String field = result.getMetaData().getColumnName(j);
          String content = result.getString(j++);

          if (content == null)
            content = "null";

          if ((field.equals("img_id") || !content.equals("null"))) {
            fields.get(i).add(content);
            // if (!field.equals("content"))
            //   System.out.println(field + ":" + content);
          }
        }
        i += 1;
      }

      stm.close();
      return fields;
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  static public ArrayList<String> get(final String name) {
    try {
      final ArrayList<String> fields = new ArrayList<String>();
      ResultSet result = null;
      int i, maxFields;
      final PreparedStatement stm = connection.getCon().prepareStatement("SELECT * FROM Item " + "WHERE name = (?)");
      stm.setString(1, name);
      result = stm.executeQuery();
      maxFields = result.getMetaData().getColumnCount();

      i = 1;
      while (i <= maxFields)
        fields.add(result.getString(i++));

      stm.close();
      return fields;
    } catch (final Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  static public boolean update(final Item item) {
    try {

      final PreparedStatement stm = connection.getCon()
          .prepareStatement("UPDATE Item " + "SET name = (?), price = (?) " + "WHERE id = (?)");
      stm.setString(1, item.getName());
      stm.setDouble(2, item.getPrice());
      stm.setInt(3, item.getID());
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