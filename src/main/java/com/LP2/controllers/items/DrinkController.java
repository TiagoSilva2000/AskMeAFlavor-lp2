package com.LP2.controllers.items;

import com.LP2.database.items.ItemDAO;
import com.LP2.models.items.Drink;
import com.LP2.models.items.Item;
import com.LP2.models.resources.Image;
import com.LP2.models.utils.Menu;

public class DrinkController extends ItemController {

  static public void create(String name, double price, String provider,
                              boolean isPresent, Image img) {
    Drink drink = new Drink(name, price, provider, isPresent, img);
    if (drink.isPresent())
      Menu.pushNewItem(drink);

    }


  static public int create(String name, double price, String provider, boolean isPresent) {
    Drink drink = new Drink(name, price, provider, isPresent);

    if (drink.isPresent())
      Menu.pushNewItem(drink);

    return 1;
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

}