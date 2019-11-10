package com.LP2.users;

import com.LP2.items.Item;
import com.LP2.utils.Menu;
import com.LP2.utils.Order;
import java.time.LocalDate;
import java.util.Scanner;

// enum constant {
//   DISCOUNT_PERCENTAGE = 10/100,
// };
public class Client extends User {
  LocalDate lastVisit; // mudar para timestamp;
  Order[] orders; // array de pedidos;
  double lastBought;

  public Client(String email, String pass, String name, String idCode) {
    super(email, pass, name, idCode);
  }

  void viewMenu(Menu currMenu) {
    currMenu.listAllItems();
  }

  // mudar para tipo Ordered.
  Item order(Menu currMenu, Item selectedItem) {
    Item[] items = currMenu.getMenu();
    byte i = 0;

    System.out.println("Pedido realizado.");
    return items[i];
  }

  // função de order que será utilizada para abstrair a order original.
  int orderMany(Menu currMenu) {
    byte itemCode = 1;
    int ordersQnt = 0;
    Scanner scan = new Scanner(System.in);

    while (itemCode != 0) {
      currMenu.listAllItems();
      itemCode = scan.nextByte();
      if (itemCode != 0) {
        order(currMenu, currMenu.getMenu()[itemCode - 1]);
        ordersQnt++;
      }
      if (itemCode > currMenu.getMenu().length || itemCode < 0) {
        System.out.println("Item não consta nos nossos registros.");
      }
    }

    scan.close();
    return ordersQnt;
  }

  void listOrders() {
    System.out.println("Pedidos até então: ");
    for (byte i = 0; i < orders.length; i++) {
      System.out.println(orders[i]);
    }
    System.out.println("Total: R$ " + getCurrentExpenses());
  }

  boolean cashBackAllowed() {
    return !(this.lastVisit.equals(LocalDate.now()));
  }

  double getCashBack() {
    return (cashBackAllowed() ? getCashBack() : 0);
  }

  double getCurrentExpenses() {
    double finalValue = 0;
    for (byte i = 0; i < this.orders.length; i++) {
      finalValue += orders[i].getCost();
    }

    return finalValue;
  }

  double payTheBill() {
    double theBill = getCurrentExpenses() - getCashBack();
    byte choice = 0;
    Scanner scan = new Scanner(System.in);

    // Retirar parte de validação quando implementarmos a interface.
    do {
      System.out.println("Selecione um método de pagamento, por favor:\n");
      System.out.println("1 - Crédito\t2 - Débito\n");
      choice = scan.nextByte();

      if (choice != 1 && choice != 2) {
        System.out.println("Selecione as opções corretas, por favor!");
      }
    } while (choice != 1 && choice != 2);

    // Adicionar algo interativo e legal sobre o pagamento.
    scan.close();
    return theBill;
  }

  void settleTheBill() {
    this.lastBought = payTheBill();
    this.lastVisit = LocalDate.now();
  }
}
