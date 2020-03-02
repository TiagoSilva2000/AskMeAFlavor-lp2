package com.LP2.server.items;

import com.LP2.database.items.ItemController;

public abstract class Item {
  private String name;
  private double price;
  private int id;
  protected boolean presentInMenu;

  public Item(String name, double price) {
    this.name = name;
    this.price = price;
    this.presentInMenu = true;
    this.id = create();
  }

  public Item(String name, double price, boolean presentInMenu) {
    this.name = name;
    this.price = price;
    this.presentInMenu = presentInMenu;
    this.id = create();
  }

  public Item(String name) {

  }

  public boolean isPresent() { return this.presentInMenu; }

  public void changePresence(boolean presentInMenu) { this.presentInMenu = presentInMenu; }

  public abstract String getExtra();

  public abstract String setExtra(String extra);

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  public int getID() { return this.id; }

  public String setName(String newName) {
    this.name = newName;

    update();
    return newName;
  }

  public double setPrice(double newPrice) {
    this.price = newPrice;

    update();
    return newPrice;
  }

  protected String inheritString() {
    return "";
  }

  public String getItsString() {
    return (
      "Nome: " + this.name + " - Preço: " + this.price + " " + inheritString()
    );
  }

  protected int create() { return ItemController.create(this); }
  protected void read() {}
  protected void update() {}
  protected void delete() {}
}
