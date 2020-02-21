package com.LP2.server.utils;

import com.LP2.server.items.Item;
import java.util.ArrayList;

public class Menu {
  static private ArrayList<Item> items;

  public Menu() {
    items = new ArrayList<Item>();
    getFromDatabase();
  }


  public byte getFromDatabase() {
    return 1;
  }

  static public void load() {
    items = new ArrayList<Item>();
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

  static boolean remItem(String name) {
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

  static public ArrayList<Item> getMenu() {
    return items;
  }
}
