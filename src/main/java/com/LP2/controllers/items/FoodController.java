package com.LP2.controllers.items;

import com.LP2.database.items.ItemDAO;
import com.LP2.models.items.Food;
import com.LP2.models.items.Item;
import com.LP2.models.resources.Image;
import com.LP2.models.utils.Menu;

public class FoodController extends ItemController{

  static public int create(final String name, final double price,
                          final String description, final boolean isPresent) {
    final Food food = new Food(name, price, description, isPresent);
    if (food.isPresent())
      Menu.pushNewItem(food);

    return 1;
  }

  static public int create(final String name, final double price,
              final String description, final boolean isPresent, final Image img) {

    final Food food = new Food(name, price, description, isPresent, img);
    if (food.isPresent())
      Menu.pushNewItem(food);
    return 1;
  }

  static public void update(final int oldId, final String uName, final double uPrice,
              final boolean uPresent, final String description, final Image uImg) {
    Item food = Menu.selectItem(oldId);
    if (food == null)
      food = new Food(oldId);
    final boolean oldPresent = food.update(uName, uPrice, description, uPresent, uImg);

    if (uPresent && !oldPresent) {
      Menu.pushNewItem(food);
    } else if (!uPresent && oldPresent) {
      Menu.rmFromMenu(oldId);
    } else if (uPresent == oldPresent) {
      Menu.updateItem(oldId, food);
    }
  }

}