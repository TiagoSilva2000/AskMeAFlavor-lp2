package com.LP2.controllers;

import com.LP2.database.items.ItemController;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;
import com.LP2.server.resources.Image;
import com.LP2.server.utils.Menu;

public class FoodVV {

  static public void create(String name, double price, String description,
                boolean isPresent) {
  Food food = new Food(name, price, description, isPresent);
  if (food.isPresent())
    Menu.pushNewItem(food);
  }

  static public void create(String name, double price, String description,
                boolean isPresent, Image img) {
    Food food = new Food(name, price, description, isPresent, img);
    if (food.isPresent())
      Menu.pushNewItem(food);
  }


  static public void read() {}

  static public void all() {}

  static public void updat(final int oldId, final String uName, final double uPrice,
                                final boolean uPresent, final String description,
                                final Image uImg) {
    Item food = Menu.selectItem(oldId);
    if (food == null)
      food = new Food(oldId);
    boolean oldPresent = food.update(uName, uPrice, description, uPresent, uImg);

    if (uPresent && !oldPresent) {
      Menu.pushNewItem(food);
    } else if (!uPresent && oldPresent) {
      Menu.rmFromMenu(oldId);
    } else if (uPresent == oldPresent) {
      Menu.updateItem(oldId, food);
    }
  }

  static public void delete(final int id) {
    Menu.rmItem(id);
    ItemController.delete(id);
  }

}