package com.LP2.users;

import com.LP2.items.Item;
import com.LP2.utils.AllOrders;
import com.LP2.utils.Menu;
import com.LP2.utils.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {
  LocalDate lastVisit;
  ArrayList<Order> orders;
  double lastBought;

  public Client(String email, String pass, String name, String idCode) {
    super(email, pass, name, idCode);
    this.orders = new ArrayList<Order>();
    this.lastVisit = LocalDate.now();
    this.lastBought = 0;
  }

  private LocalDate setLastVisit(LocalDate currVisit) {
    this.lastVisit = currVisit;

    return currVisit;
  }

  private double setLastBought(double currBought) {
    this.lastBought = currBought;

    return currBought;
  }

  public LocalDate getLastVisit() {
    return this.lastVisit;
  }

  public double getLastBought() {
    return this.lastBought;
  }

  public void viewMenu(Menu currMenu) {
    currMenu.listAllItems();
  }

  // mudar para tipo Ordered.
  public Order order(Item selectedItem, byte qnt, AllOrders allOrders) {
    Order order = new Order(selectedItem, qnt);
    this.orders.add(order);
    allOrders.pushOrder(order);

    return order;
  }

  // função de order que será utilizada para abstrair a order original.
  public int orderMany(Menu currMenu, AllOrders allOrders) {
    byte itemCode = 1, ordersQnt = 0, itemQnt;
    Scanner scan = new Scanner(System.in);

    while (itemCode != 0) {
      currMenu.listAllItems();
      itemCode = scan.nextByte();
      itemQnt = scan.nextByte();
      if (itemCode != 0) {
        order(currMenu.getMenu().get(itemCode - 1), itemQnt, allOrders);
        ordersQnt++;
      }
      if (itemCode > currMenu.getMenu().size() || itemCode < 0) {
        System.out.println("Item não consta nos nossos registros.");
      }
    }

    scan.close();
    return ordersQnt;
  }

  public void listOrders() {
    System.out.println("Pedidos até então: ");
    for (byte i = 0; i < orders.size(); i++) {
      System.out.println(orders.get(i));
    }
    System.out.println("Total: R$ " + getCurrentExpenses());
  }

  boolean cashBackAllowed() {
    return !(this.lastVisit.equals(LocalDate.now()));
  }

  public double getCashBack() {
    return (cashBackAllowed() ? (10 / 100 * lastBought) : 0);
  }

  public double getCurrentExpenses() {
    double finalValue = 0;
    for (byte i = 0; i < this.orders.size(); i++) {
      finalValue += orders.get(i).getCost();
    }

    return finalValue;
  }

  private double payTheBill() {
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

  public void settleTheBill() {
    setLastBought(payTheBill());
    setLastVisit(LocalDate.now());

    saveInDatabase();
  }
}
