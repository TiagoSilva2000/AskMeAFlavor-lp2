package com.LP2.models.items;

import com.LP2.database.items.ItemDAO;
import com.LP2.models.resources.Image;

public class Item {
  private String name;
  private double price;
  protected int id;
  protected boolean presentInMenu;
  protected Image img;


  // Default Constructor without Image
  public Item(String name, double price) {
    this.name = name;
    this.price = price;
    this.presentInMenu = true;
    this.img = null;
    this.id = ItemDAO.create(this);
  }

  // Default Constructor with Image
  public Item(String name, double price, Image img) {
    this.name = name;
    this.price = price;
    this.presentInMenu = true;
    this.img = img;
    this.id = ItemDAO.create(this);
  }

  public Item(String name, double price, Image img, int id) {
    this.name = name;
    this.price = price;
    this.presentInMenu = true;
    this.img = img;
    this.id = id;
  }

  public Item(String name, double price, Image img, boolean presentInMenu) {
    this.name = name;
    this.price = price;
    this.presentInMenu = presentInMenu;
    this.img = img;
    this.id = ItemDAO.create(this);
  }

  public Item(final int id, final String name, final double price,
              final boolean present, final Image img) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.presentInMenu = present;
    this.img = img;
  }

  public Item(String name, double price, boolean presentInMenu) {
    this.name = name;
    this.price = price;
    this.presentInMenu = presentInMenu;
    this.img = null;
    this.id = ItemDAO.create(this);

  }

  public Item(final Item item) {
    this.id = item.id;
    this.name = item.name;
    this.price = item.price;
    this.img = item.img;
    this.presentInMenu = item.presentInMenu;
  }

  public Item(final int id) {
    Item item = ItemDAO.read(id);
    this.id = item.id;
    this.name = item.name;
    this.price = item.price;
    this.img = item.img;
    this.presentInMenu = item.presentInMenu;
  }


  public Image getImage() { return this.img; }

  public boolean isPresent() { return this.presentInMenu; }

  public void changePresence(boolean presentInMenu) { this.presentInMenu = presentInMenu; }

  public String getExtra() { return "HAHAHA"; };

  public String setExtra(String extra) { return ""; };

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  public int getID() { return this.id; }

  public String setName(String newName) {
    this.name = newName;

    // update();
    return newName;
  }

  public double setPrice(double newPrice) {
    this.price = newPrice;

    // update();
    return newPrice;
  }

  protected String inheritString() {
    return "";
  }

  public String getItsString() {
    return (
      "ID:" + this.id + " - Nome: " + this.name + " - Preço: " + this.price + " " + inheritString()
    );
  }

  protected void read() {}

  public boolean update(final String name, final double price, final String extra,
                        final boolean present, final Image img) {
    boolean oldPresence = this.presentInMenu;
    this.name = name;
    this.price = price;
    this.presentInMenu = present;
    this.img = img;
    ItemDAO.update(this);

    return oldPresence;
  }

  public void delete() {}
}
