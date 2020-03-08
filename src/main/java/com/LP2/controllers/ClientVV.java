package com.LP2.controllers;

import com.LP2.server.users.Client;

public class ClientVV {

  static public Client create(final String name, final String email,
        final String password, final String idCode) {

    return new Client(name, email, password, idCode);
  }


  public void read () {}


  public void update() {}


  public void delete() {}


  public void all() {}

}

