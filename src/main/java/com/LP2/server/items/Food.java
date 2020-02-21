package com.LP2.server.items;

public class Food extends Item {
  String description;

  public Food(String name, double price, String description) {
    super(name, price);
    this.description = description;

    super.create();
  }

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
}
