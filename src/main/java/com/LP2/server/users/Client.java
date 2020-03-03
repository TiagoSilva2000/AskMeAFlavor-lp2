package com.LP2.server.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.LP2.app.Reader;
import com.LP2.database.users.ClientController;
import com.LP2.server.items.Item;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Constants;
import com.LP2.server.utils.Menu;
import com.LP2.server.utils.Order;

public class Client extends User {
  LocalDate lastVisit;
  ArrayList<Order> orders;
  double lastBought;

  public Client(String name, String email, String password, String idCode) {
    super(name, email, password, idCode, Constants.getClientCode());
    this.orders = new ArrayList<Order>();
    this.lastVisit = null;
    this.lastBought = 0;
    ClientController.create(this);
  }

  public Client(String username, String password) {
    super(username, password);
    setExtraInfoById();
  }

  public Client(User user){
    super(user);
    setExtraInfoById();
  }

  public void setExtraInfoById() {
    ArrayList<String> fields = ClientController.get(this.id);
    if (fields.get(1) == null)
      lastBought = 0;
    else
      lastBought = Double.parseDouble(fields.get(1));

    if (fields.get(2) != null) {
      lastVisit = LocalDate.parse(fields.get(2));
    } else {
      lastVisit = null;
    }
    this.orders = new ArrayList<Order>();
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

  public ArrayList<Order> getOrders() { return this.orders; }

  public void viewMenu() {
    Menu.listAllItems();
  }

  private Order order(Item selectedItem, byte qnt) {
    Order order = new Order(selectedItem, qnt, this.id);
    this.orders.add(order);
    AllOrders.pushOrder(order);

    return order;
  }

  public int getOrdersQnt() { return this.orders.size(); }

  public int orderMany() {
    byte itemCode = 1, ordersQnt = 0, itemQnt;
    Scanner scan = new Scanner(System.in);

    while (itemCode != 0) {
      Menu.listAllItems();
      itemCode = scan.nextByte();
      itemQnt = scan.nextByte();
      if (itemCode != 0) {
        order(Menu.getMenu().get(itemCode - 1), itemQnt);
        ordersQnt++;
      }
      if (itemCode > Menu.getMenu().size() || itemCode < 0) {
        System.out.println("Item não consta nos nossos registros.");
      }
    }

    scan.close();
    return ordersQnt;
  }

  public void addOrders(ArrayList<Order> orders) {
    for (Order asked : orders) {
      this.order(asked.getItem(), (byte)asked.getQnt());
    }
  }

  public void listOrders() {
    System.out.println("Pedidos até então: ");
    for (byte i = 0; i < orders.size(); i++) {
      System.out.println(orders.get(i));
    }
    System.out.println("Total: R$ " + getCurrentExpenses());
  }

  private boolean cashBackAllowed() {
    if (this.lastVisit == null) {
      this.lastVisit = LocalDate.now();
    }

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

    // Retirar parte de validação quando implementarmos a interface.
    do {
      System.out.println("Selecione um método de pagamento, por favor:\n");
      System.out.println("1 - Crédito\t2 - Débito\n");
      choice = (byte) Integer.parseInt(Reader.getScanner().nextLine());

      if (choice != 1 && choice != 2) {
        System.out.println("Selecione as opções corretas, por favor!");
      }
    } while (choice != 1 && choice != 2);

    // Adicionar algo interativo e legal sobre o pagamento.
    return theBill;
  }

  public void order(Order order) {
    this.orders.add(order);
    AllOrders.pushOrder(order);
  }

  public double settleTheBill() {
    setLastBought(payTheBill());
    setLastVisit(LocalDate.now());

    updateVisit();
    System.out.println("Passed!");
    return this.lastBought;
  }

  public void delete() {
    super.delete();
    ClientController.remove(this.id);
  }

  private void updateVisit() { ClientController.update(this); }
}
