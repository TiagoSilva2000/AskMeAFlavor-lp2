package com.LP2.server.users;

import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.utils.Constants;
import com.LP2.server.utils.Menu;

public class Manager extends User {
  private String secret;

  // vai carregar, do banco, as informações do admin e chamar o super.
  public Manager(
    String email,
    String pass,
    String name,
    String idCode,
    String secret
  ) {
    super(email, pass, name, idCode, Constants.getManagerCode());
  }

  // comparar os hashes de cada um.
  public boolean isSecret(String inSecret) {
    return this.secret.equals(inSecret);
  }

  public Drink registerDrink(
    Menu menu,
    String name,
    double price,
    String provider
  ) {
    Drink drink = new Drink(name, price, provider);
    menu.pushNewItem(drink);

    return drink;
  }

  public Food registerFood(
    Menu menu,
    String name,
    double price,
    String description
  ) {
    Food food = new Food(name, price, description);
    menu.pushNewItem(food);

    return food;
  }

  public void registerCook() {}
}
