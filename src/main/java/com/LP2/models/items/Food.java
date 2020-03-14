package com.LP2.models.items;

import com.LP2.database.items.FoodDAO;
import com.LP2.models.resources.Image;

public class Food extends Item {
  private String description;

  public Food(String name, double price, String description) {
    super(name, price);
    this.description = description;
    FoodDAO.create(this);
  }

  public Food(String name, double price, String description, boolean present, Image img) {
    super(name, price, img, present);
    this.description = description;
    FoodDAO.create(this);
  }

  public Food(int id, String name, double price, String description, Image img) {
    super(name, price, img, id);
    this.description = description;
    this.presentInMenu = true;
  }

  public Food(String name, double price, String description, boolean isPresent) {
    super(name, price, isPresent);
    this.description = description;
    FoodDAO.create(this);
  }

  public Food (final Item item, final String desc) {
    super(item);
    this.description = desc;
  }

  public Food(final int id) {
    super(id);
    Food food = FoodDAO.read(this);
    this.description = food.description;
  }

  public String getDesc() {
    return this.description;
  }

  public String getExtra() {
    return this.description;
  }

  public String setDesc(String newDescription) {
    this.description = newDescription;

    // super.update();

    return newDescription;
  }

  public String setExtra(String extra) {
    this.description = extra;

    return extra;
  }

  protected String inheritString() {
    return "- Descrição: " + this.description;
  }

  public boolean update(final String name, final double price, final String desc,
                      final boolean present, final Image img) {
    boolean oldPresence = super.update(name, price, desc, present, img);
    this.description = desc;
    FoodDAO.update(this);

    return oldPresence;
  }

}
