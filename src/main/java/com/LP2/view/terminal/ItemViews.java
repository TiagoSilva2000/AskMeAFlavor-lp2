package com.LP2.view.terminal;

import java.util.ArrayList;

import com.LP2.app.Reader;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;


public class ItemViews {

  static public Item createItem() {
    char option;
    do {
      System.out.println(
        "1 - Food\n" +
        "2 - Drink"
      );
      option = Screen.readOption("12");
    } while(option != '\0');

    return buildItem((byte)option);
  }

  static private Item buildItem(byte option) {
    Item newItem = null;
    String name, extra;
    double price;

    try {
      System.out.println("Enter product's name: ");
      name = Reader.getScanner().nextLine();
      System.out.println("Enter product's price: ");
      price = Reader.getScanner().nextDouble();
      if (option == 1) {
        System.out.println("Enter food's description: ");
        extra = Reader.getScanner().nextLine();
        return new Food(name, price, extra);
      } else {
        System.out.println("Enter drink's provider: ");
        extra = Reader.getScanner().nextLine();
        return new Drink(name, price, extra);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.exit(403);
      return null;
    }
  }

  static public String removeItem() {
    String name = null;
    char option = '0';

    while(option == '0') {
      System.out.println("Enter product's name: ");
      name = Reader.getScanner().nextLine();
      do {
        System.out.println(
          "1 - Confirma\n" +
          "2 - Corrigir\n" +
          "0 - Cancela"
          );
          option = Screen.readOption("012");
        } while(option == '\0');
    }

    return name;
  }

  static public boolean editItem(Item item) {
    String extra = item instanceof Food ? "Description" : "Provider", input;
    double price;
    char option;
    boolean running = true;

    do {
      System.out.println(
        "[1] - Name: " + item.getName() + "\n" +
        "[2] - Price: " + item.getPrice() + "\n" +
        "[3] - " + extra + ": " + item.getExtra() + "\n" +
        "[0] - Save and Exit"
      );
      option = Screen.readOption("0123");
      switch(option) {
        case 1:
          System.out.println("Username: ");
          input = Reader.getScanner().nextLine();
          item.setName(input);
          break;
        case 2:
          System.out.println("Price: ");
          price = Reader.getScanner().nextDouble();
          item.setPrice(price);
          break;
        case 3:
          System.out.println(extra + ": ");
          input = Reader.getScanner().nextLine();
          item.setExtra(input);
          break;
        case 0:
          running = false;
          break;
        default:
          System.out.println("ERROR 404");
          System.exit(2);
      }
    } while(running);

    return true;
  }

  static public boolean indexItems(ArrayList<Item> items) {
    for (Item item : items) {
      System.out.println(item.getItsString());
    }
    return true;
  }

  static public boolean checkItem(Item item) {
    System.out.println(item.getID() + "\n" + item.getItsString());

    return true;
  }

}