package com.LP2.models.users;

import java.util.ArrayList;
import java.util.Scanner;

import com.LP2.database.users.UserDAO;
import com.LP2.models.items.Item;
import com.LP2.models.utils.Order;

public class User {
  protected String email, password, name, idCode, phone;
  protected int id;
  protected byte usertype;

  public User() {
  }

  public User(final int id, final String name, final String email, final String idCode, final String phone,
      final byte usertype) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.idCode = idCode;
    this.phone = phone;
    this.usertype = usertype;
    this.password = null;
  }

  public User(User user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getName();
    this.idCode = user.getIDCode();
    this.id = user.getID();
    this.phone = user.phone;
    this.usertype = user.getUsertype();
  }

  public User(final String name, final String email, final String password, final String idCode, final String phone,
      final byte userType) {
    setAll(name, email, password, idCode, phone, userType);
    this.id = create();
  }

  public User(final String username, final String password) {
    this.name = username;
    this.password = password;
    this.id = -1;

    User tmp = read();
    if (tmp == null) {
      return;
    }
    try {
      setAll(tmp.name, tmp.email, tmp.password, tmp.idCode, tmp.phone, tmp.usertype);

      this.id = tmp.getID();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public User(final int id) {

    User tmp = read();
    if (tmp == null) {
      return;
    }
    try {
      setAll(tmp.name, tmp.email, tmp.password, tmp.idCode, tmp.phone, tmp.usertype);

      this.id = tmp.getID();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setExtraInfoById() {
  }

  private void setAll(final String name, final String email, final String password, final String idCode,
      final String phone, final byte userType) {
    this.email = email;
    this.name = name;
    this.phone = phone;
    this.password = password;
    this.idCode = idCode;
    this.usertype = userType;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return this.email;
  }

  public int getID() {
    return this.id;
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

  public byte getUsertype() {
    return this.usertype;

  }

  public double getCurrentExpenses() {
    return -1;
  }

  public double getCashBack() {
    return -1;
  }

  public int getVisitId() {
    return -1;
  }

  public ArrayList<Order> getOrders() {
    return null;
  }

  public Object[][] getOrdersMatrix() {
    return null;
  }

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

  public void setPassword(String password) {
    this.password = password;
  }

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

  public double settleTheBill() {
    return -1;
  }

  public Order order(final Item item, final int qnt) {
    return null;
  }

  public int getOrdersQnt() {
    return -1;
  }

  public byte setUsertype(byte usertype) {
    this.usertype = usertype;
    return usertype;
  }

  public byte setUsertype(String usertype) {
    this.usertype = (byte) Integer.parseInt(usertype);

    return this.usertype;
  }

  protected int create() {
    return UserDAO.create(this);
  }

  protected User read() {
    return UserDAO.read(this.name, this.password);
  }

  public void update() {
    UserDAO.update(this);
  }

  public void update(final String name, final String password, final String email, final String idCode,
      final String phone) {
    this.setEmail(email);
    this.setPassword(password);
    this.setIDCode(idCode);
    this.setName(name);
    this.setPhone(phone);
    UserDAO.update(this);
  }

  protected void delete() {
    UserDAO.delete(this.id);
  }

  static public User create(final String name, final String email, final String password,
                  final String idCode, final String phone, final byte usertype) {


    return null;
  }

  static public User read(final int id) {
    User user = UserDAO.read(id);

    return user;
  }

  static public byte update(final User user) {
    byte result = UserDAO.update(user);

    return result;
  }

  static public void delete(final int id) {
    UserDAO.delete(id);
  }

  static public ArrayList<User> all(final byte userType) {
    ArrayList<User> users = UserDAO.all(userType);

    return users;
  }
}
