package com.LP2.view.terminal;

import java.util.ArrayList;

import com.LP2.app.Reader;
import com.LP2.server.items.Item;
import com.LP2.server.users.Client;
import com.LP2.server.users.User;
import com.LP2.server.utils.Constants;
import com.LP2.server.utils.Menu;
import com.LP2.server.utils.Order;

public class ClientViews {
  static private String option;
  static private char charOption;

  static public char renderLobby () {
    do {
      System.out.println(
        "1 - Menu\n" +
        "2 - Order an Item\n" +
        "3 - Current Orders\n" +
        "4 - Settle the Bill\n" +
        "5 - Check Profile\n" +
        "0 - Return"
      );
      charOption = Screen.readOption("012345");
    } while (charOption == '\0');

    return charOption;
  }

  static public void renderMenu() {
    for (Item item : Menu.getMenu()) {
      System.out.println(item.getItsString());
    }
  }

  static public ArrayList<Order> renderOrder () {
    ArrayList<Order> menuChoices = new ArrayList<Order>();
    byte currByte;
    int qnt;

    System.out.println("Which items in our menu would you like to?");
    while (!(option = Reader.getScanner().nextLine()).equals("0")) {
      currByte = (byte) Integer.parseInt(option);
      if (Menu.hasCode(currByte)) {
        System.out.println("Quantity: ");
        qnt = Integer.parseInt(Reader.getScanner().nextLine());
        Order order = new Order(Menu.getMenu().get(currByte), qnt);
        menuChoices.add(order);
      } else {
        System.out.println("Invalid Code!");
      }
    }

    return menuChoices;
  }

  static public char renderProfile (User user) {
    char choice;

    System.out.println(
      "[1] - Username: " + user.getName() + "\n" +
      "[2] - Email: " + user.getEmail() + "\n" +
      "[3] - IDCode: " + user.getIDCode() + "\n" +
      "[4] - Password: " + "********" + "\n" +
      "[5] - User Type: " + Constants.getUserString(user.getUsertype()) + "\n" +
      "[0] - Apply Changes and Return."
    );
    choice = Screen.readOption("012345");

    return choice;
  }

  static public void renderOrders (Client client) {
    System.out.println("Name --- Price --- Quantity --- Ordered in --- Status");
    for (Order order : client.getOrders()) {
      System.out.println(
        order.getItem().getName() + " " +
        order.getItem().getPrice() + " " +
        order.getQnt() + " " +
        order.getTime() + " " +
        Constants.getOrderString(order.getStatus()) + "\n"
      );
    }
  }

  static public boolean renderBill (Client client) {
    String choice;

    renderOrders(client);
    System.out.println("Total: " + client.getCurrentExpenses());
    System.out.println("Are you sure that you want to settle the bill? [yes/no]\n");
    choice = Reader.getScanner().nextLine().toLowerCase();

    return choice.equals("yes");
  }

  static public double finishPayment(double total) {
    double income, change;

    System.out.println("Adicione o dinheiro a ser inserido: ");
    income = Double.parseDouble(Reader.getScanner().nextLine());
    change = total - income;
    System.out.println("Change: " + change);

    return change;
  }
}