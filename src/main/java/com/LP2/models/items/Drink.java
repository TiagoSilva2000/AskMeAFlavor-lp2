package com.LP2.models.items;

import com.LP2.database.items.DrinkDAO;
import com.LP2.models.resources.Image;

public class Drink extends Item {
  String provider;

  public Drink(String name, double price, String provider) {
    super(name, price);
    this.provider = provider;
    DrinkDAO.create(this);
  }

  public Drink(String name, double price, String provider, boolean present, Image img) {
    super(name, price, img, present);
    this.provider = provider;
    DrinkDAO.create(this);
  }

  public Drink(final int id, final String name, final double price,
              final String provider, final Image img) {
    super(name, price, img, id);
    this.provider = provider;
  }

  public Drink(String name, double price, String provider, boolean isPresent) {
    super(name, price, isPresent);
    this.provider = provider;
    DrinkDAO.create(this);
  }

  public Drink (final Item item, final String prov) {
    super(item);
    this.provider = prov;
  }

  public Drink(final int id) {
    super(id);
    Drink drink = DrinkDAO.read(this);
    this.provider = drink.provider;
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

    return newProvider;
  }

  protected String inheritString() {
    return "- Provider: " + this.provider;
  }

  public boolean update(final String name, final double price, final String provider,
                      final boolean present, final Image img) {
    boolean oldPresence = super.update(name, price, provider, present, img);
    this.provider = provider;
    DrinkDAO.update(this);

    return oldPresence;
  }
}
