package com.LP2.server.items;

import com.LP2.database.items.FoodController;
import com.LP2.server.resources.Image;

public class Food extends Item {
  private String description;

  public Food(String name, double price, String description) {
    super(name, price);
    this.description = description;
    FoodController.create(this);
  }

  public Food(String name, double price, String description, Image img) {
    super(name, price, img);
    this.description = description;
    FoodController.create(this);
  }

  public Food(String name, double price, String description, boolean isPresent) {
    super(name, price, isPresent);
    this.description = description;
    FoodController.create(this);
  }

  public Food(final int id) { super(id); }

  public String getDesc() {
    return this.description;
  }

  public String getExtra() {
    return this.description;
  }

  public String setDesc(String newDescription) {
    this.description = newDescription;

    super.update();;

    return newDescription;
  }

  public String setExtra(String extra) {
    this.description = extra;

    return extra;
  }

  protected String inheritString() {
    return "- Descrição: " + this.description;
  }

  public void update(final String name, final double price, final String desc,
                    final Image img) {}

}
