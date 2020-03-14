package com.LP2.models.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.LP2.database.misc.VisitDAO;
import com.LP2.database.users.ClientDAO;
import com.LP2.models.items.Item;
import com.LP2.models.utils.AllOrders;
import com.LP2.models.utils.Constants;
import com.LP2.models.utils.Menu;
import com.LP2.models.utils.Order;
import com.LP2.models.utils.Visit;

public class Client extends User {
  private LocalDate lastVisit;
  private ArrayList<Order> orders;
  private double lastBought;
  private Visit visit;

  public Client(String name, String email, String password, String idCode, String phone) {
    super(name, email, password, idCode, phone, Constants.getClientCode());
    this.orders = new ArrayList<Order>();
    this.lastVisit = null;
    this.lastBought = 0;
    visit = null;
    ClientDAO.create(this);
  }

  public Client(String username, String password) {
    super(username, password);
    setExtraInfoById();
  }

  public Client(User user){
    super(user);
    setExtraInfoById();
  }

  public Client(final int id, final String name, final String email, final String idCode, final String phone,
              final byte usertype, final double lastBought, final LocalDate date) {
    super(id, name, email, idCode, phone, usertype);
    this.lastBought = lastBought;
    this.lastVisit = date;
  }


  public void setExtraInfoById() {
    ArrayList<String> fields = ClientDAO.read(this.id);
    final int openVisitId = VisitDAO.getOpenVisit(this.id);
    this.orders = AllOrders.ordersFromUser(this.id);


    if (openVisitId != -1)
      this.visit = new Visit(openVisitId, true);

    this.lastBought = Double.parseDouble(fields.get(0));
    if (fields.get(1) != null) {
      lastVisit = LocalDate.parse(fields.get(1));
    } else {
      lastVisit = null;
    }
  }

  public int getVisitId() { return this.visit.getId(); }

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

  public Order order(final Item selectedItem, final int qnt) {
    if (this.orders.size() == 0 && this.visit != null)
      this.visit = new Visit(this.id);
    else if (this.visit == null)
      this.visit = new Visit(this.id);
    Order order = new Order(selectedItem, qnt, this.id, this.visit.getId());
    this.orders.add(order);

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

  public Object[][] getOrdersMatrix() {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();
    for (int i = 0; i < orders.size(); i++) {
      String status = "Preparando...";
      if (orders.get(i).getStatus() == Constants.getFinishedOrder())
        status = "Entregue.";
      else if (orders.get(i).getStatus() == Constants.getCanceledOrder())
        status = "Cancelado";
      else if (orders.get(i).getStatus() == Constants.getPaidOrder())
        status = "Pago";

      String out = String.format("%.2f", orders.get(i).getItem().getPrice());
      if (status.equals("Entregue.") || status.equals("Preparando..."))
      objs.add(new Object[]{
        orders.get(i).getID(),
        orders.get(i).getItem().getName(),
        orders.get(i).getQnt(),
        out,
        status
      });
    }
    Object[][] ob = objs.toArray(new Object[0][]);
    return ob;
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
    return (cashBackAllowed() ? (6.0 / 100 * lastBought) : 0);
    // return (6.0 / 100 * this.lastBought);
  }

  public double getCurrentExpenses() {
    double finalValue = 0;
    for (byte i = 0; i < this.orders.size(); i++) {
      finalValue += orders.get(i).getCost();
    }

    return finalValue;
  }

  private double payTheBill() {

    return getCurrentExpenses() - getCashBack();
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
    ClientDAO.remove(this.id);
  }

  private void updateVisit() { ClientDAO.update(this); }

  static public Client read(final int id) {
    Client client = ClientDAO.read(id, true);

    return client;
  }

  static public byte update(final Client client) {
    byte result = ClientDAO.update(client);

    return result;
  }

}
