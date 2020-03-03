package com.LP2.app;

import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.database.items.FoodController;
import com.LP2.database.items.ItemController;
import com.LP2.database.misc.Historic;
import com.LP2.database.users.ClientController;
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

final public class Router {
  private User loggedUser;
  private Menu menu;
  private AllOrders currentOrders;

  public Router(final Connect db) {
    this.loggedUser = null;
    this.menu = new Menu();
    this.currentOrders = new AllOrders();
  }

  public void closeSession() {
    this.menu = null;
    this.loggedUser = null;
    this.currentOrders = null;
  }

  public void login(final String username, final String password) {
    this.loggedUser = new User(username, password);
  }

  public void sigin(final String name, final String email, final String password, final String idCode) {
    this.loggedUser = new Client(name, email, password, idCode);
  }

  public void logout() {
    this.loggedUser = null;
  }

  public void storeFood(String name, double price, String description, boolean isPresent) {
    Food food = new Food(name, price, description, isPresent);
    if (food.isPresent())
      Menu.pushNewItem(food);
  }

  public void storeDrink(String name, double price, String provider, boolean isPresent) {
    Drink drink = new Drink(name, price, provider, isPresent);

    if (drink.isPresent())
      Menu.pushNewItem(drink);
  }

  public void updateFood(final int id, final String name, final double price,
                        final String desc, final Image img) {
    new Food(id).update(name, price, desc, img);;
  }

  public void updateDrink(final int id, final String name, final double price,
                        final String provider, final Image img) {
    new Drink(id).update(name, price, provider, img);;
  }

  public void deleteItem(final int id) { Menu.rmItem(id); }

  public void deleteItem(final String name) { Menu.rmItem(name); }

  public void storeCook(final String name, final String email,
                        final String password, final String idCode) {
    new Cook(email, password, name, idCode);
  }

  public void updateCook(final int id, final String name, final String email,
                        final String password, final String idCode) {
    new Cook(id).update(name, password, email, idCode);
  }

  public void deleteCook(final int id) {
    if (loggedUser.getUsertype() == Constants.getManagerCode())
      ClientController.remove(id);
  }

  private void startVisit() {
    Historic.startVisit();
  }

  private void finishVisit() {
    Historic.finishVisit();
  }

  public void order(final String itemName, final int qnt) {
    Order order = new Order(Menu.selectItem(itemName), qnt, loggedUser.getID());
    int ordersQnt = loggedUser.getOrdersQnt();
    if (ordersQnt == 0)
      startVisit();
    loggedUser.order(order);
  }

  public void order(final int itemID, final int qnt) {
    Order order = new Order(Menu.selectItem(itemID), qnt, loggedUser.getID());
    int ordersQnt = loggedUser.getOrdersQnt();
    if (ordersQnt == 0)
      startVisit();
    loggedUser.order(order);
  }

  public void closeOrder(final int id) { AllOrders.remOrder(id); }

  public double processPayment() {
    double paidValue = loggedUser.settleTheBill();
    finishVisit();

    return paidValue;
  }

  public void reloadMenu(boolean all) {
    this.menu = null;
    this.menu = new Menu();
  }

  public void recoverOrders() {
    this.currentOrders = new AllOrders();
  }

/////////// TEMPORARY

  public void storeReview(ArrayList<Review> rev) {}

  public ArrayList<Review> getUserReviews() { return null; }

  public ArrayList<Review> getAllReviews() { return null; }

  public void deleteReview() {}

}