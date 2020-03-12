package com.LP2.controllers;

import com.LP2.database.items.ItemController;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Item;
import com.LP2.server.resources.Image;
import com.LP2.server.utils.Menu;

public class DrinkVV {

  static public void create(String name, double price, String provider,
                              boolean isPresent, Image img) {
    Drink drink = new Drink(name, price, provider, isPresent, img);
    if (drink.isPresent())
      Menu.pushNewItem(drink);

    }


  static public void create(String name, double price, String provider, boolean isPresent) {
    Drink drink = new Drink(name, price, provider, isPresent);

    if (drink.isPresent())
    Menu.pushNewItem(drink);
  }

  static public void read() {}

  static public void all() {}

  static public void update(final int oldId, final String uName, final double uPrice,
                                final boolean uPresent, final String provider,
                                final Image uImg) {
    Item drink = Menu.selectItem(oldId);
    if (drink == null)
      drink = new Drink(oldId);
    boolean oldPresent = drink.update(uName, uPrice, provider, uPresent, uImg);

    if (uPresent && !oldPresent) {
      Menu.pushNewItem(drink);
    } else if (!uPresent && oldPresent) {
      Menu.rmFromMenu(oldId);
    } else if (uPresent == oldPresent) {
      Menu.updateItem(oldId, drink);
    }
  }

  static public void delete(final int id) {
    Menu.rmItem(id);
    ItemController.delete(id);
  }
}