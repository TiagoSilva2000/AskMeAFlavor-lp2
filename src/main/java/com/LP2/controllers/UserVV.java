package com.LP2.controllers;

import com.LP2.database.users.UserController;

public class UserVV {

  static public void create() {}

  static public void read() {}

  static public void all() {}

  static public int update(final int id, final String name, final String email, final String cpf
                          ) {
    LoginVV.getuser().setEmail(email);
    LoginVV.getuser().setName(name);
    LoginVV.getuser().setIDCode(cpf);
    UserController.update(LoginVV.getuser());
    return 1;
  }

  static public void delete() {}

}