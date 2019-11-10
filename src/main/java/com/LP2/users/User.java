package com.LP2.users;

import java.util.Scanner;

public abstract class User {
  String email, password, name, idCode;

  public User(String email, String pass, String name, String idCode) {
    this.email = email;
    this.name = name;
    this.password = pass; // aplicar hash aqui.
    this.idCode = idCode; // aplicar hash aqui.

    saveInDatabase();
  }

  public String getName() {
    return this.name;
  }

  String getEmail() {
    return this.email;
  }

  // aplicar hash aqui.
  String getPassword() {
    return this.password;
  }

  // aplicar hash aqui
  String getIDCode() {
    return this.idCode;
  }

  void saveInDatabase() {}

  void resetPassword() {
    Scanner scan = new Scanner(System.in);
    String newPass, passConfirm;
    do {
      System.out.println("Insira a sua nova senha: ");
      newPass = scan.nextLine();
      System.out.println("Confirme: ");
      passConfirm = scan.nextLine();

      if (!newPass.equals(passConfirm)) {
        System.out.println("Senhas não batem!!");
      }
    } while (!(newPass.equals(passConfirm)));

    // aplicar hash aqui.
    this.password = newPass;

    scan.close();
    saveInDatabase();
  }

  void resetEmail() {
    Scanner scan = new Scanner(System.in);
    String newEmail;
    System.out.println("Insira o seu novo endereço de email: ");
    newEmail = scan.nextLine();
    this.email = newEmail;

    scan.close();
    saveInDatabase();
  }
}
