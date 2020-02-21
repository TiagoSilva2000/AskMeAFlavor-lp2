package com.LP2.server.items;

public class Drink extends Item {
  String provider;

  public Drink(String name, double price, String provider) {
    super(name, price);
    this.provider = provider;

    super.create();
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
}
