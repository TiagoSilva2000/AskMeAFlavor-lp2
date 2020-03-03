package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;

public class App {

  public static void main(String[] args) {
    Connect db = null;

    Loader.preLoad();
    db = new Connect();
    Loader.loadControllers(db);



    // Drink drink = new Drink("refri", 10, "coca-cola");

    // img = new Image(System.getProperty("user.dir") + "/public/", "img1", ".svg");
    // Food food = new Food("arroz", 4, "temperada com sal a gosto e azeite", img);
    // db.addColumnToTable("ALTER TABLE Item ADD COLUMN present BOOLEAN NOT NULL DEFAULT TRUE");

    // for (int i = 0; i < Menu.getMenu().size(); i++)
    //   System.out.println(Menu.getMenu().get(i).getItsString());

    // Drink drink = new Drink("refri", 80, "Coca-cola");

    // db.addColumnToTable("DELETE FROM Food");
    // db.addColumnToTable("DELETE FROM Drink");
    // db.addColumnToTable("DELETE FROM Item");
    // db.addColumnToTable("DELETE FROM Image");

    // Login.main(args);
    // Session = new Session(db);
    // Login.main(args);

    Session.close();
    Loader.unloadStreams();
  }
}