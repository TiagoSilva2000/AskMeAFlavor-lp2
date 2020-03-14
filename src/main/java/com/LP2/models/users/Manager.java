package com.LP2.models.users;

import com.LP2.models.items.Drink;
import com.LP2.models.items.Food;
import com.LP2.models.utils.Constants;
import com.LP2.models.utils.Menu;

public class Manager extends User {
  private String secret;

  // vai carregar, do banco, as informações do admin e chamar o super.
  public Manager(
    final String email,
    final String pass,
    final String name,
    final String idCode,
    final String phone
  ) {
    super(name, email, pass, idCode, phone, Constants.getManagerCode());
  }

  public Manager(final User user) {
    super(user);
    this.secret = "secret";
  }

  // comparar os hashes de cada um.
  public boolean isSecret(final String inSecret) {
    return this.secret.equals(inSecret);
  }

  public Drink registerDrink(final Menu menu, final String name, final double price,
                            final String provider) {
    final Drink drink = new Drink(name, price, provider);
    Menu.pushNewItem(drink);

    return drink;
  }

  public Food registerFood(final Menu menu, final String name, final double price,
                          final String description) {
    final Food food = new Food(name, price, description);
    Menu.pushNewItem(food);

    return food;
  }

  public void registerCook() {}
}
