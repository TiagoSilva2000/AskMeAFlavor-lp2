package com.LP2.server.utils;

import com.LP2.database.items.ItemController;
import com.LP2.server.items.Item;
import java.util.ArrayList;

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

  static public void load() {
    items = new ArrayList<Item>();
    ArrayList<ArrayList<String>> fields = ItemController.all();

    for (int i = 0; i < fields.size(); i++) {
      for (int j = 0; j < fields.get(i).size(); j++)
        System.out.println(fields.get(i).get(j));

      System.out.println("\n");
    }
    System.out.println("Passed!");
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

  static private boolean rmFromMenu (final String name) {
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

  static private boolean rmFromMenu (final int id) {
    byte i = 0;

    while (i < items.size() && (items.get(i).getID() != id)) i++;

    if (i == items.size()) {
      return false;
    } else {
      items.remove(i);
    }

    return true;
  }

  static public boolean rmItem(final String name) {
    boolean success = rmFromMenu(name);

    if (success)
      ItemController.remove(name);

    return success;
  }

  static public boolean rmItem(final int id) {
    boolean success = rmFromMenu(id);

    if (success)
      ItemController.remove(id);

    return success;
  }


  static public ArrayList<Item> getMenu() {
    return items;
  }
}
