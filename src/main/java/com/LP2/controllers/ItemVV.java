package com.LP2.controllers;

import com.LP2.database.items.ItemController;
import com.LP2.server.items.Item;

public class ItemVV {
  static private int itemID;

  static public int getItemID() { return itemID; }

  static public void setItemID(final int nID) { itemID = nID; }

  static public void create() {}

  static public Item read() {
    return ItemController.read(itemID);
  }

  static public void all() {}

  static public void update() {}

  static public void delete() {}

}