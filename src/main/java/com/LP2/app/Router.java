package com.LP2.app;

import java.util.ArrayList;

import com.LP2.database.Connect;
import com.LP2.database.items.FoodController;
import com.LP2.database.items.ItemController;
import com.LP2.server.feedback.Review;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.users.Client;
import com.LP2.server.users.User;
import com.LP2.server.utils.AllOrders;
import com.LP2.server.utils.Menu;
import com.LP2.server.utils.Order;

final public class Router {
  private User loggedUser;
  private Menu menu;
  private AllOrders currentOrders;
  private Connect db;

  public Router(final Connect db) {
    this.db = db;
    this.loggedUser = null;
    this.menu = new Menu();
    this.currentOrders = new AllOrders();
  }

  public void closeSession() {
    this.db = null;
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

  public void updateFood() {}

  public void updateDrink() {}

  public void deleteItem(final int id) { ItemController.remove(id); }

  public void deleteItem(final String name) { ItemController.remove(name); }

  public void storeCook() {}

  public void updateCook() {}

  public void deleteCook() {}

  public void loadItem() {}

  private void startVisit() {}

  private void finishVisit() {}

  public void order(final String itemName, final int qnt) {
    startVisit();
    AllOrders.pushOrder(new Order(Menu.selectItem(itemName), qnt));
  }

  public void order(final int itemID, final int qnt) {
    startVisit();
    AllOrders.pushOrder(new Order(Menu.selectItem(itemID), qnt));
  }

  public void closeOrder() {}

  public void processPayment() {

    finishVisit();
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