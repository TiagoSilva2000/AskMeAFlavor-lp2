package com.LP2.utils;

import com.LP2.items.Item;

public class Menu {
  Item[] items;

  public Menu() {
    getFromDatabase();
  }

  byte getFromDatabase() {
    return 1;
  }

  public void listAllItems() {
    for (byte i = 0; i < items.length; i++) {
      System.out.println(
        (i + 1) +
          " - Item: " +
          items[i].getName() +
          "\nPreço: " +
          items[i].getPrice()
      );
    }
  }

  Item pushNewItem(Item newItem) {
    // items.push(newItem);
    return newItem;
  }

  boolean remItem(String name) {
    byte i = 0;

    while (i < items.length && (!items[i].getName().equals(name))) i++;

    if (i == items.length) {
      System.out.println("Produto não encontrado no menu!");
      return false;
    } else {
      // remover item do array aqui.
    }

    return true;
  }

  public Item[] getMenu() {
    return this.items;
  }
}
