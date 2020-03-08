package com.LP2.server.users;

import java.util.ArrayList;
import java.util.Scanner;

import com.LP2.database.users.UserController;
import com.LP2.server.items.Item;
import com.LP2.server.utils.Order;

public class User {
  protected String email, password, name, idCode;
  protected int id;
  protected byte usertype;

  public User() {}

  public User(final int id, final String name, final String email,
              final String idCode, final byte usertype) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.idCode = idCode;
    this.usertype = usertype;
    this.password = null;
  }

  public User(User user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getName();
    this.idCode = user.getIDCode();
    this.id = user.getID();
    this.usertype = user.getUsertype();
  }

  protected User(String name, String email, String password, String idCode, byte userType) {
    setAll(name, email, password, idCode, userType);
    this.id = create();
  }

  public User (final String username, final String password) {
    this.name = username; this.password = password;
    ArrayList<String> fields = read();
    if (fields.size() == 0) {
      return;
    }
    try {
      if (fields.size() != 0) {
        byte usertype = (byte) Integer.parseInt(fields.get(5));
        setAll(fields.get(1), fields.get(2), fields.get(3), fields.get(4), usertype);
      }
      this.id = Integer.parseInt(fields.get(0));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setExtraInfoById() {

  }

  private void setAll(String name, String email, String password, String idCode,
  byte userType) {
    this.email = email;
    this.name = name;
    this.password = password; // aplicar hash aqui.
    this.idCode = idCode; // aplicar hash aqui.
    this.usertype = userType;
  }

  public String getEmail() {
    return this.email;
  }

  public int getID() { return this.id; }

  public String getName() {
    return this.name;
  }

  public String getIDCode() {
    return this.idCode;
  }

  public String getPassword() {
    return this.password;
  }

  public byte getUsertype() {
    return this.usertype;

  }

  public double getCurrentExpenses() { return -1; }

  public double getCashBack() { return -1; }

  public int getVisitId() { return -1; }

  public ArrayList<Order> getOrders() { return null; }

  public Object[][] getOrdersMatrix() { return null; }

  // mandar e-mail de confirmação aqui.
  public String setEmail(String nEmail) {
    this.email = nEmail;

    update();
    return nEmail;
  }

  public String setName(String nName) {
    this.name = nName;

    update();
    return nName;
  }

  public void setPassword(String password) { this.password = password; }

  public void setPassword() {
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
    update();
  }

  public String setIDCode(String IDCode) {
    this.idCode = IDCode;

    update();
    return IDCode;
  }

  public double settleTheBill() { return -1; }

  public Order order(final Item item, final int qnt) { return null; }

  public int getOrdersQnt() { return -1; }

  public byte setUsertype(byte usertype) { this.usertype = usertype; return usertype; }

  public byte setUsertype(String usertype) {
    this.usertype = (byte) Integer.parseInt(usertype);

    return this.usertype;
  }

  protected int create() { return UserController.create(this); }

  protected ArrayList<String> read() { return UserController.get(this.name, this.password); }

  public void update() { UserController.update(this); }

  public void update(final String name, final String password, final String email,
                    final String idCode) {
    this.setEmail(email);
    this.setPassword(password);
    this.setIDCode(idCode);
    this.setName(name);
    UserController.update(this);
  }

  protected void delete() { UserController.remove(this.id); }

}
