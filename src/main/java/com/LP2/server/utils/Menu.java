package com.LP2.server.utils;

import com.LP2.server.items.Item;
import java.util.ArrayList;

public class Menu {
  private ArrayList<Item> items;

  public Menu() {
    this.items = new ArrayList<Item>();
    getFromDatabase();
  }

  byte getFromDatabase() {
    return 1;
  }

  public void listAllItems() {
    for (byte i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + "  " + items.get(i).getItsString());
    }
  }

  public Item pushNewItem(Item newItem) {
    items.add(newItem);
    return newItem;
  }

  boolean remItem(String name) {
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

  public ArrayList<Item> getMenu() {
    return this.items;
  }
}
