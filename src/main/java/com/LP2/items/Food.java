package com.LP2.items;

public class Food extends Item {
  String description;

  public Food(String name, double price, String description) {
    super(name, price);
    this.description = description;

    super.saveInDatabase();
  }

  String getDesc() {
    return this.description;
  }

  String setDesc(String newDescription) {
    this.description = newDescription;

    super.saveInDatabase();

    return newDescription;
  }

  protected String inheritString() {
    return "- Descrição: " + this.description;
  }
}
