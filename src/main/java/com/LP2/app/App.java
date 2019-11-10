package com.LP2.app;

import com.LP2.users.Client;

public class App {

  public static void main(String[] args) {
    Client newClient = new Client(
      "tiago10moreira1@gmail.com",
      "12345",
      "Tiago Silva",
      "08102842512"
    );
    System.out.println(newClient.getName());
  }
}
