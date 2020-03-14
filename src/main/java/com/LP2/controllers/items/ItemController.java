package com.LP2.controllers.items;

import java.util.ArrayList;

import com.LP2.database.items.ItemDAO;
import com.LP2.models.items.Item;
import com.LP2.models.utils.Menu;

public class ItemController {

  static public Item read(final int id, final boolean includeAll) {
    Item it = Menu.selectItem(id);

    if (it == null && includeAll)
      it = ItemDAO.read(id);

    return it;
  }

  static public ArrayList<Item> all(final byte presenceCode) {
    ArrayList<Item> items = ItemDAO.all(presenceCode);

    return items;
  }

  static public boolean delete(final int id) {
    boolean wasRemoved = Menu.rmFromMenu(id);

    if (wasRemoved)
      wasRemoved = ItemDAO.delete(id);

    return wasRemoved;
  }

}