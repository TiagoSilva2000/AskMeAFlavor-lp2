package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
import com.LP2.database.items.ItemController;
import com.LP2.server.items.Drink;
import com.LP2.server.items.Food;
import com.LP2.server.resources.Image;
import com.LP2.server.utils.Menu;
import com.LP2.view.pages.Login;

public class App {

  public static void main(String[] args) {
    Connect db = null;
    Router router = null;
    Image img = null;

    Loader.preLoad();
    db = new Connect();
    Loader.loadControllers(db);

    // img = new Image(System.getProperty("user.dir") + "/public/", "img1", ".svg");
    // Food food = new Food("arroz", 4, "temperada com sal a gosto e azeite", img);
    // Menu.load();
    // db.addColumnToTable("ALTER TABLE Item ADD COLUMN itemType SMALLINT NOT NULL");

    // for (int i = 0; i < Menu.getMenu().size(); i++)
    //   System.out.println(Menu.getMenu().get(i).getItsString());

    // Drink drink = new Drink("refri", 80, "Coca-cola");

    // db.addColumnToTable("DELETE FROM Food");
    // db.addColumnToTable("DELETE FROM Drink");
    // db.addColumnToTable("DELETE FROM Item");
    // db.addColumnToTable("DELETE FROM Image");

    Login.main(args);
    // db.createOrderTable();
    // db.deleteTable("clientorder");
    // router = new Router(db);
    // Login.main(args);

    Loader.unloadStreams();
  }
}