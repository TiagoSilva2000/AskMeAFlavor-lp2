package com.LP2.app;

import java.util.ArrayList;

import com.LP2.database.misc.Historic;
import com.LP2.database.users.UserController;
import com.LP2.server.feedback.Review;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.resources.Image;
import com.LP2.server.users.Client;
import com.LP2.server.users.Cook;
import com.LP2.server.users.User;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Constants;
import com.LP2.server.utils.Menu;
import com.LP2.server.utils.Order;

final public class Session {
  static private User loggedUser;
  static private Menu menu;
  static private AllOrders currentOrders;

  static public void load() {
    loggedUser = null;
    menu = new Menu();
    currentOrders = new AllOrders();
  }

  static public void close() {
    menu = null;
    loggedUser = null;
    currentOrders = null;
  }

  static public void login(final String username, final String password) {
    loggedUser = new User(username, password);
  }

  static public void sigin(final String name, final String email, final String password, final String idCode) {
    loggedUser = new Client(name, email, password, idCode);
  }

  static public void logout() {
    loggedUser = null;
  }

  static public void storeFood(String name, double price, String description, boolean isPresent) {
    Food food = new Food(name, price, description, isPresent);
    if (food.isPresent())
      Menu.pushNewItem(food);
  }

  static public void storeDrink(String name, double price, String provider, boolean isPresent) {
    Drink drink = new Drink(name, price, provider, isPresent);

    if (drink.isPresent())
      Menu.pushNewItem(drink);
  }

  static public void updateFood(final int id, final String name, final double price,
                        final String desc, final Image img) {
    new Food(id).update(name, price, desc, img);
  }

  static public void updateDrink(final int id, final String name, final double price,
                        final String provider, final Image img) {
    new Drink(id).update(name, price, provider, img);
  }

  static public void deleteItem(final int id) { Menu.rmItem(id); }

  static public void deleteItem(final String name) { Menu.rmItem(name); }

  static public void storeCook(final String name, final String email,
                        final String password, final String idCode) {
    new Cook(email, password, name, idCode);
  }

  static public void updateCook(final int id, final String name, final String email,
                        final String password, final String idCode) {
    new Cook(id).update(name, password, email, idCode);
  }

  static public void deleteCook(final int id) {
    if (loggedUser.getUsertype() == Constants.getManagerCode())
      UserController.remove(id);
  }

  static private void startVisit() {
    Historic.startVisit();
  }

  static private void finishVisit() {
    Historic.finishVisit();
  }

  static public void order(final String itemName, final int qnt) {
    Order order = new Order(Menu.selectItem(itemName), qnt, loggedUser.getID());
    int ordersQnt = loggedUser.getOrdersQnt();
    if (ordersQnt == 0)
      startVisit();
    loggedUser.order(order);
  }

  static public void order(final int itemID, final int qnt) {
    Order order = new Order(Menu.selectItem(itemID), qnt, loggedUser.getID());
    int ordersQnt = loggedUser.getOrdersQnt();
    if (ordersQnt == 0)
      startVisit();
    loggedUser.order(order);
  }

  static public void closeOrder(final int id) { AllOrders.remOrder(id); }

  static public double processPayment() {
    double paidValue = loggedUser.settleTheBill();
    finishVisit();

    return paidValue;
  }

  static public void reloadMenu(boolean all) {
    menu = null;
    menu = new Menu();
  }

  static public void recoverOrders() {
    currentOrders = new AllOrders();
  }

/////////// TEMPORARY

  static public void storeReview(ArrayList<Review> rev) {}

  static public ArrayList<Review> getUserReviews() { return null; }

  static public ArrayList<Review> getAllReviews() { return null; }

  static public void deleteReview() {}

}