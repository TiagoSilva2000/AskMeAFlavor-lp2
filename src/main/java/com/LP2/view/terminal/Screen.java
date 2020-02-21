package com.LP2.view.terminal;

import com.LP2.app.Reader;
import com.LP2.server.users.User;


public class Screen {
  static private char option;

  static public char renderMain () {
    do {
      System.out.println(
        "1 - Create Account\n" +
        "2 - Log in\n" +
        "3 - Log out\n" +
        "0 - Exit"
      );
    } while (readOption("0123") == '\0');

    return option;
  }

  static public void renderExit() { System.out.println("Até a próxima!"); }

  static public User renderSignin() {
    User tempUser = new User();
    String passConf;

    System.out.println("Username: ");
    tempUser.setName(Reader.getScanner().nextLine());

    System.out.println("Email: ");
    tempUser.setEmail(Reader.getScanner().nextLine());

    System.out.println("ID code: ");
    tempUser.setIDCode(Reader.getScanner().nextLine());

    System.out.println("User type (ALFA): ");
    tempUser.setUsertype(Reader.getScanner().nextLine());

    do {
      System.out.println("Password: ");
      tempUser.setPassword(Reader.getScanner().nextLine());
      System.out.println("Password Confirmation: ");
      passConf = Reader.getScanner().nextLine();
    } while (!passConf.equals(tempUser.getPassword()));

    return tempUser;
  }

  static public User renderLogin() {
    User user = new User();

    System.out.println("Username: ");
    user.setName(Reader.getScanner().nextLine());
    System.out.println("Password: ");
    user.setPassword(Reader.getScanner().nextLine());

    return user;
  }

  static public char readOption (String acceptedCodes) {
    String readLine = Reader.getScanner().nextLine();
    char currentChar;

    if (acceptedCodes.length() == 0) {
      return option;
    }

    for (int i = 0; i < acceptedCodes.length(); i++) {
      currentChar = acceptedCodes.charAt(i);
      if (readLine.equals(String.valueOf(currentChar))) {
        option = currentChar;
        return option;
      }
    }


    System.out.println("Erro na leitura!");
    return '\0';
  }
}