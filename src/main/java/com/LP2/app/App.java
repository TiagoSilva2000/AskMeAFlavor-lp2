package com.LP2.app;

import com.LP2.database.Connect;
import com.LP2.users.*;
// import com.LP2.utils.AllOrders;
// import com.LP2.utils.Menu;

public class App {

  public static void main(String[] args) {
    Connect db = new Connect();
    Manager manager = new Manager(
      "tiago10moreira1@gmail.com",
      "123456",
      "Maneiro Bacano",
      "123",
      "segredo"
    );
    db.insertUserTable(manager);
  }
}
// public static void main(String[] args) {
//   Client client = new Client(
//     "tiago10moreira1@gmail.com",
//     "12345",
//     "Tiago Silva",
//     "08102842512"
//   );
//   Cook cook = new Cook(
//     "tiago10moreira1@gmail.com",
//     "123456789",
//     "Yahaha",
//     "08102842512"
//   );
//   Menu menu = new Menu();
//   AllOrders allOrders = new AllOrders();
//   manager.registerDrink(menu, "Coca-cola", 36d, "Capitalismo");
//   manager.registerFood(menu, "Shield", 80f, "Captain");
//   client.viewMenu(menu);
//   client.order(menu.getMenu().get(0), (byte) 3, allOrders);
//   cook.checkOpenOrders(allOrders);
//   cook.closeOrder(allOrders);
//   cook.checkOpenOrders(allOrders);
//   System.out.println(client.getCurrentExpenses());
//   client.settleTheBill();
