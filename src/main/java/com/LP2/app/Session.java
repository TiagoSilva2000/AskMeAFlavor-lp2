package com.LP2.app;

import java.util.ArrayList;

import com.LP2.database.items.ItemController;
import com.LP2.database.misc.OrderController;
import com.LP2.database.misc.VisitController;
import com.LP2.database.users.UserController;
import com.LP2.server.feedback.Review;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;
import com.LP2.server.resources.Image;
import com.LP2.server.users.Client;
import com.LP2.server.users.Cook;
import com.LP2.server.users.Manager;
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
    if (loggedUser.getUsertype() == Constants.getClientCode())
      loggedUser = new Client(loggedUser);
    else if (loggedUser.getUsertype() == Constants.getManagerCode())
      loggedUser = new Manager(loggedUser);
    else
      loggedUser = new Cook(loggedUser);
  }

  static public void sigin(final String name, final String email, final String password, final String idCode) {
    loggedUser = new Client(name, email, password, idCode);
  }

  static public void logout() {
    loggedUser = null;
  }

  static public User getLoggedUser() { return loggedUser; }

  static public void storeFood(String name, double price, String description,
                              boolean isPresent) {
    Food food = new Food(name, price, description, isPresent);
    if (food.isPresent())
      Menu.pushNewItem(food);
  }

  static public void storeFood(String name, double price, String description,
                              boolean isPresent, Image img) {
    Food food = new Food(name, price, description, isPresent, img);
    if (food.isPresent())
      Menu.pushNewItem(food);
  }

  static public void storeDrink(String name, double price, String provider,
                              boolean isPresent, Image img) {
    Drink drink = new Drink(name, price, provider, isPresent, img);
    if (drink.isPresent())
      Menu.pushNewItem(drink);
  }


  static public void storeDrink(String name, double price, String provider, boolean isPresent) {
    Drink drink = new Drink(name, price, provider, isPresent);

    if (drink.isPresent())
    Menu.pushNewItem(drink);
  }

  static public void updateFood(final int oldId, final String uName, final double uPrice,
                                final boolean uPresent, final String description,
                                final Image uImg) {
    Item food = Menu.selectItem(oldId);
    if (food == null)
      food = new Food(oldId);
    boolean oldPresent = food.update(uName, uPrice, description, uPresent, uImg);

    if (uPresent && !oldPresent) {
      Menu.pushNewItem(food);
    } else if (!uPresent && oldPresent) {
      Menu.rmFromMenu(oldId);
    } else if (uPresent == oldPresent) {
      Menu.updateItem(oldId, food);
    }
  }

  static public void updateDrink(final int oldId, final String uName, final double uPrice,
                                final boolean uPresent, final String provider,
                                final Image uImg) {
    Item drink = Menu.selectItem(oldId);
    if (drink == null)
      drink = new Drink(oldId);
    boolean oldPresent = drink.update(uName, uPrice, provider, uPresent, uImg);

    if (uPresent && !oldPresent) {
      Menu.pushNewItem(drink);
    } else if (!uPresent && oldPresent) {
      Menu.rmFromMenu(oldId);
    } else if (uPresent == oldPresent) {
      Menu.updateItem(oldId, drink);
    }
  }

  static public void deleteItem(final int id) {
    Menu.rmItem(id);
    ItemController.remove(id);
  }

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
  }

  static private void finishVisit() {
  }

  // static public void order(final String itemName, final int qnt) {
  //   if (loggedUser == null)
  //     return;

  //   Order order = new Order(Menu.selectItem(itemName), qnt, loggedUser.getID());
  //   int ordersQnt = loggedUser.getOrdersQnt();
  //   if (ordersQnt == 0)
  //     startVisit();
  //   loggedUser.order(order);
  // }

  static public void order(final int itemID, final int qnt) {
    Item selectedItem = Menu.selectItem(itemID);
    if (loggedUser == null || selectedItem == null)
      return;
    Order order = loggedUser.order(selectedItem, qnt);
    if (order == null) {
      System.out.println("Erro...");
      System.exit(343);
    }
    AllOrders.pushOrder(order);
  }

  static public void closeOrder(final int id) {
    Order tmp = AllOrders.remOrder(id);
    if (tmp != null) {
      OrderController.update(tmp);
      // atualizar pedidos do cliente também
    }
  }

  static public void processPayment() {
    int userOrdersQnt = AllOrders.ordersQntFromUser(loggedUser.getID());
    if (userOrdersQnt == 0)
      return;

    if (userOrdersQnt > 0) {
      System.out.println("Wait until all of orders be finished please");
      return;
    }
    double paidValue = loggedUser.settleTheBill();
    VisitController.update(loggedUser.getVisitId(), paidValue);

    logout();
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