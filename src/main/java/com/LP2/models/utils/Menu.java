package com.LP2.models.utils;

import com.LP2.database.items.ItemDAO;
import com.LP2.models.items.Drink;
import com.LP2.models.items.Food;
import com.LP2.models.items.Item;
import com.LP2.models.resources.Image;
import java.util.ArrayList;
import java.util.List;

public class Menu {
  static private ArrayList<Item> items;

  static public void load(final byte presenceCode) {
    items = ItemDAO.all(presenceCode);
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
      String out = String.format("%.2f", items.get(i).getPrice());
      objs.add(new Object[]{
        items.get(i).getID(),
        items.get(i).getName(),
        out
      });
    }
    Object[][] ob = objs.toArray(new Object[0][]);

    return ob;
  }

  static public Object[][] getMatrixMenuToManager() {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();
    String type, desc, prov, extra;
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i) instanceof Food) {
        type = "comida";
        desc = items.get(i).getExtra();
        prov = null;
        extra = items.get(i).getExtra();
      } else {
        type = "bebida";
        prov = items.get(i).getExtra();
        extra = items.get(i).getExtra();
        desc = null;
      }

      String out = String.format("%.2f", items.get(i).getPrice());
      objs.add(new Object[]{
        items.get(i).getID(),
        items.get(i).getName(),
        out,
        type,
        extra
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
