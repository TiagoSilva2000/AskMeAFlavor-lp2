package com.LP2.server.utils;

import com.LP2.database.items.ItemController;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;
import com.LP2.server.resources.Image;
import java.util.ArrayList;
import java.util.List;

public class Menu {
  static private ArrayList<Item> items;

  // public Menu() {
  //   items = new ArrayList<Item>();
  //   load();
  // }


  // public byte getFromDatabase() {
  //   ArrayList<ArrayList<String>> fields = ItemController.all();
  //   return 1;
  // }

  static public void load(final byte presenceCode) {
    items = new ArrayList<Item>();
    ArrayList<ArrayList<String>> fields = ItemController.all(presenceCode);
    int lastPos;
    Image img = null;
    String name, extra, filename, filepath, filetype;
    double price;
    int imgId, id;
    byte[] content = null;

    img = null;
    filename = null;
    filepath= null;
    filetype = null;

    for (int i = 0; i < fields.size(); i++) {
      lastPos = fields.get(i).size() - 1;
      filepath = filename = filetype = null; imgId = -1; img = null; content = null;
      id = Integer.parseInt(fields.get(i).get(0));
      name = fields.get(i).get(1);
      price = Double.parseDouble(fields.get(i).get(2));
      extra = fields.get(i).get(4);

      if (!fields.get(i).get(3).equals("null")) {
        imgId = Integer.parseInt(fields.get(i).get(3));
        filepath = fields.get(i).get(5);
        filename = fields.get(i).get(6);
        filetype = fields.get(i).get(7);
        content = fields.get(i).get(8).getBytes();
        img = new Image(imgId, filepath, filename, filetype, content);
      }
      if (fields.get(i).get(lastPos).equals("food")) {
        items.add(new Food(id, name, price, extra, img));
      } else {
        items.add(new Drink(id, name, price, extra, img));
      }
    }
  }

  static public void updateItem(final int id, final Item item) {
    for (int i = 0; i < items.size(); i++)
      if (items.get(i).getID() == id)
        items.set(i, item);
  }

  static public Item selectItemAt(final int idx) {
    if (idx < 0 || idx > items.size())
      return null;

    return items.get(idx);
  }

  static public Item selectItem(final String name) {
    int i = 0;

    while (i < items.size() && !items.get(i).getName().equals(name))
      i += 1;

    if (i == items.size())
      return null;

    return items.get(i);
  }

  static public Item selectItem(final int code) {
    int i = 0;

    while (i < items.size() && code != items.get(i).getID())
      i += 1;

    if (i == items.size())
      return null;

    return items.get(i);
  }

  // static public Food selectFood(final int code) {

  //   return selectItem(code);
  // }

  // static public Drink drink(final int code) {

  //   return selectItem(code);
  // }

  static public boolean hasCode(int code) { return code >= 0 && code <= items.size(); }

  static public void listAllItems() {
    for (byte i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + "  " + items.get(i).getItsString());
    }
  }

  static public Item pushNewItem(Item newItem) {
    items.add(newItem);
    return newItem;
  }

  static public boolean rmFromMenu (final String name) {
    byte i = 0;

    while (i < items.size() && (!items.get(i).getName().equals(name))) i++;

    if (i == items.size()) {
      System.out.println("Produto não encontrado no menu!");
      return false;
    } else {
      items.remove(i);
    }

    return true;
  }

  static public boolean rmFromMenu (final int id) {
    byte i = 0;

    while (i < items.size() && (items.get(i).getID() != id)) i++;

    if (i == items.size()) {
      return false;
    } else {
      items.remove(i);
    }

    return true;
  }

  static public Object[][] getMatrixMenu() {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();
    for (int i = 0; i < items.size(); i++) {
      objs.add(new Object[]{
        items.get(i).getID(),
        items.get(i).getName(),
        items.get(i).getPrice()
      });
    }
    Object[][] ob = objs.toArray(new Object[0][]);

    return ob;
  }

  static public Object[][] getMatrixMenuToManager() {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();
    String type, desc, prov;
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i) instanceof Food) {
        type = "comida";
        desc = items.get(i).getExtra();
        prov = null;
      } else {
        type = "bebida";
        prov = items.get(i).getExtra();
        desc = null;
      }

      objs.add(new Object[]{
        items.get(i).getID(),
        items.get(i).getName(),
        items.get(i).getPrice(),
        type,
        prov,
        desc
      });
    }
    Object[][] ob = objs.toArray(new Object[0][]);
    System.out.println(ob.length);
    return ob;
  }

  static public boolean rmItem(final String name) {
    boolean success = rmFromMenu(name);

    return success;
  }

  static public boolean rmItem(final int id) {
    boolean success = rmFromMenu(id);

    return success;
  }

  static public ArrayList<Item> getMenu() {
    return items;
  }
}
