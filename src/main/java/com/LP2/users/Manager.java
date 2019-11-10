package com.LP2.users;

public class Manager extends User {

  public Manager(
    String email,
    String pass,
    String name,
    String idCode,
    String secret
  ) {
    super(email, pass, name, idCode);
  }

  void registerDrink() {}

  void registerFood() {}

  void registerCook() {}
}
