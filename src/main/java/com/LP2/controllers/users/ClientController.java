package com.LP2.controllers.users;

import com.LP2.models.users.Client;

public class ClientController {

  static public Client create(final String name, final String email,
        final String password, final String idCode, final String phone) {
    return new Client(name, email, password, idCode, phone);
  }


  static public Client read (final int id) {
    Client client = Client.read(id);

    return client;
  }

  static public byte update(final Client client) {
    byte result = Client.update(client);

    return result;
  }
}

