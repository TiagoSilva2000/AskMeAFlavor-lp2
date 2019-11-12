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

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public String getIDCode() {
    return this.idCode;
  }

  public String getPassword() {
    return this.password;
  }
  // mandar e-mail de confirmação aqui.
  public String setEmail(String nEmail) {
    this.email = nEmail;

    saveInDatabase();
    return nEmail;
  }

  public String setName(String nName) {
    this.name = nName;

    saveInDatabase();
    return nName;
  }

  void setPassword() {
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
    scan = null;
    saveInDatabase();
  }

  public String setIDCode(String IDCode) {
    this.idCode = IDCode;

    saveInDatabase();
    return IDCode;
  }

  protected void saveInDatabase() {}
}
