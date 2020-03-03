package com.LP2.server.items;

import com.LP2.database.items.DrinkController;
import com.LP2.server.resources.Image;

public class Drink extends Item {
  String provider;

  public Drink(String name, double price, String provider) {
    super(name, price);
    this.provider = provider;
    DrinkController.create(this);
  }

  public Drink(String name, double price, String provider, Image img) {
    super(name, price, img);
    this.provider = provider;
    DrinkController.create(this);
  }

  public Drink(String name, double price, String provider, Image img, int id) {
    super(name, price, img, id);
    this.provider = provider;
  }

  public Drink(String name, double price, String provider, boolean isPresent) {
    super(name, price, isPresent);
    this.provider = provider;
    DrinkController.create(this);
  }

  public Drink(final int id) {
    super(id);
  }

  public String getProvider() {
    return this.provider;
  }

  public String getExtra() {
    return this.provider;
  }

  public String setExtra(String extra) {
    this.provider = extra;

    return extra;
  }

  public String setProvider(String newProvider) {
    this.provider = newProvider;

    super.update();;
    return newProvider;
  }

  protected String inheritString() {
    return "- Provider: " + this.provider;
  }

  public void update(final String name, final double price, final String provider,
                    final Image img) {

  }
}
