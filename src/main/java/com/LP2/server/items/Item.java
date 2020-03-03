package com.LP2.server.items;

import com.LP2.database.items.ItemController;
import com.LP2.server.resources.Image;

public abstract class Item {
  private String name;
  private double price;
  protected int id;
  protected boolean presentInMenu;
  protected Image img;


  public Item(String name, double price) {
    this.name = name;
    this.price = price;
    this.presentInMenu = true;
    this.img = null;
    this.id = ItemController.create(this);
  }

  public Item(String name, double price, Image img) {
    this.name = name;
    this.price = price;
    this.presentInMenu = true;
    this.img = img;
    this.id = ItemController.create(this);
  }

  public Item(String name, double price, boolean presentInMenu) {
    this.name = name;
    this.price = price;
    this.presentInMenu = presentInMenu;
    this.img = null;
    this.id = ItemController.create(this);
  }

  public Item(final int id) {}

  public Item(String name) {

  }


  public Image getImage() { return this.img; }

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

  protected void read() {}
  protected void update() {}
  protected void delete() {}
}
