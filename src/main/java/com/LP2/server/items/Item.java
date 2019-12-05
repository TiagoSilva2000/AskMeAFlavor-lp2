package com.LP2.server.items;

public abstract class Item {
  private String name;
  private double price;

  public Item(String name, double price) {
    this.name = name;
    this.price = price;
    saveInDatabase();
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  String setName(String newName) {
    this.name = newName;

    saveInDatabase();
    return newName;
  }

  double setPrice(double newPrice) {
    this.price = newPrice;

    saveInDatabase();
    return newPrice;
  }

  byte saveInDatabase() {
    return 1;
  }

  protected String inheritString() {
    return "";
  }

  public String getItsString() {
    return (
      "Nome: " + this.name + " - Preço: " + this.price + " " + inheritString()
    );
  }
}
