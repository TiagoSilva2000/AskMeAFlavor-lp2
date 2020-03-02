package com.LP2.view.terminal.routes;

import java.util.ArrayList;
import com.LP2.app.Reader;
import com.LP2.database.items.DrinkController;
import com.LP2.database.items.FoodController;
import com.LP2.database.items.ItemController;
import com.LP2.server.items.Food;
import com.LP2.server.items.Item;
import com.LP2.server.users.Client;
import com.LP2.server.users.Cook;
import com.LP2.server.users.Manager;
import com.LP2.server.users.User;
import com.LP2.server.utils.Constants;
import com.LP2.server.utils.Order;
import com.LP2.view.terminal.ClientViews;
import com.LP2.view.terminal.ItemViews;
import com.LP2.view.terminal.ManagerViews;
import com.LP2.view.terminal.Screen;

public class Router {
  private Client client;
  private Cook cook;
  private Manager manager;
  private byte option;

  public void initialize() {
    client = null;
    cook = null;
    manager = null;
    this.main();
  }

  private void main() {
    User tmp = null;
    User user = null;
    boolean keepItRunning = true;

    while (keepItRunning) {
      this.option = (byte) (Screen.renderMain() - '0');
      switch(option) {
        case 1:
          tmp = Screen.renderSignin();
          client = new Client(tmp.getName(), tmp.getEmail(),
                              tmp.getPassword(), tmp.getIDCode());
          clientRoutes(client);
          break;
        case 2:
          tmp = Screen.renderLogin();
          user = new User(tmp.getName(), tmp.getPassword());

          if (user.getUsertype() == Constants.getClientCode()) {
            this.client = new Client(user); cook = null; manager = null;
            clientRoutes(client);
          } else if (user.getUsertype() == Constants.getManagerCode()) {
            this.manager = new Manager(user); client = null; cook = null;
            managerRoutes(manager);
          } else {
            this.cook = new Cook(user); client = null; manager = null;
            cookRoutes(cook);
          }
          break;
        case 3:
          logout();
          break;
        case 0:
          Screen.renderExit();
          keepItRunning = false;
          break;
        default:
          keepItRunning = false;
          System.out.println("ERROR 404");
          break;
      }
    }
  }

  private void clientRoutes (Client client) {
    boolean keepItRunning = true;
    this.option = 2;

    while (keepItRunning) {
      this.option = (byte) (ClientViews.renderLobby() - '0');
      System.out.println(this.option);
      switch(option) {
        case 0:
          keepItRunning = false;
          break;
        case 1:
          ClientViews.renderMenu();
          break;
        case 2:
          ArrayList<Order> orders = ClientViews.renderOrder();
          client.addOrders(orders);
          break;
        case 3:
          ClientViews.renderOrders(client);
          break;
        case 4:
          double tmpBill;
          if(ClientViews.renderBill(client)) {
            tmpBill = client.settleTheBill();
            ClientViews.finishPayment(tmpBill);
            keepItRunning = false;
          }
          break;
        case 5:
          profileRoutes(client);
          break;
        default:
          System.out.println("Unaccepted choice! Choose another one.");
          break;
      }
    }
    return;
  }

  private void profileRoutes(User user) {
    boolean running = true;
    String value = null;
    do {
      option = (byte) (ClientViews.renderProfile(user) - '0');
      switch(option) {
        case 1:
          System.out.println("Username: ");
          value = Reader.getScanner().nextLine();
          user.setName(value);
          break;
        case 2:
          System.out.println("Email: ");
          value = Reader.getScanner().nextLine();
          user.setEmail(value);
          break;
        case 3:
          System.out.println("ID code: ");
          value = Reader.getScanner().nextLine();
          user.setIDCode(value);
          break;
        case 4:
          String passConf = null;
          do {
            System.out.println("Password: ");
            value = Reader.getScanner().nextLine();
            System.out.println("Password Confirmation: ");
            passConf = Reader.getScanner().nextLine();
          } while (!value.equals(passConf));
          user.setPassword(passConf);
          break;
        case 5:
          System.out.println("Hey, you can't do that!");
          break;
        case 0:
          user.update();
          running = false;
          break;
        default:
          System.out.println("Unallowed Formatt!");
          break;
      }
    } while (running);
  }

  private void managerRoutes(Manager manager) {
    boolean running = true;

    do {
      option = (byte) (ManagerViews.renderLobby() - '0');
      switch(option) {
        case 1:
          itemsRoutes();
          break;
        case 2:
          managerCookRoutes();
          break;
        case 0:
          running = false;
          break;
      }
    } while (running);
  }

  private void itemsRoutes() {
    boolean running = true;

    do {
      option = (byte) (ManagerViews.renderItemLobby() - '0');
      switch(option) {
        case 1:
          Item item = ItemViews.createItem();
          if (item instanceof Food)
            FoodController.create(item);
          else
            DrinkController.create(item);
          break;
        case 2:
          String itemName = ItemViews.removeItem();
          if (itemName != null)
            ItemController.remove(itemName);
          break;
        case 3:
          String name;
          Item itemm = null;

          System.out.println("Name: ");
          name = Reader.getScanner().nextLine();
          // itemm = ItemController.getItem (name);
          ItemViews.editItem(itemm);
          ItemController.update(itemm);
          break;
        case 4:
          break;
        case 5:
          break;
        case 0:
          running = false;
          break;
        default:
          System.out.println("ERROR 404");
          break;
      }
    } while (running);

  }

  private void managerCookRoutes() {
    boolean running = true;

    do {
      option = (byte) (ManagerViews.renderCookLobby() - '0');
      switch(option) {
        case 1:
          break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 0:
          running = false;
          break;
        default:
          System.out.println("ERROR 404");
          break;
      }
    } while(running);
  }


  private void cookRoutes(Cook cook) {


  }

  private void logout() {
    if (client == null && manager == null && cook == null) {
      System.out.println("Usuário não logado!");
    } else {
      System.out.println("Log out realizado com sucesso.");
      client = null;
      manager = null;
      cook = null;
    }
  }

}