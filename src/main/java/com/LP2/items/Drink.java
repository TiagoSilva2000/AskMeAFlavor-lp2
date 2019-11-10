package com.LP2.items;

public class Drink extends Item {
  String provider;

  public Drink(String name, double price, String provider) {
    super(name, price);
    this.provider = provider;
  }

  String getProvider() {
    return this.provider;
  }

  String setProvider(String newProvider) {
    this.provider = newProvider;

    super.saveInDatabase();
    return newProvider;
  }
}
