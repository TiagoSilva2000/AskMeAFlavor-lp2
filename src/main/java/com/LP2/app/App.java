package com.LP2.app;

import com.LP2.app.loader.Loader;
import com.LP2.database.Connect;
// import com.LP2.database.users.UserTable;
// import com.LP2.server.users.*;
// import com.LP2.utils.AllOrders;
// import com.LP2.utils.Menu;

public class App {

  public static void main(String[] args) {
    Loader.load();

    new Connect();
    // UserTable userTable = new UserTable(db);
    // Manager manager = new Manager(
    //   "myemail@hotmail.com",
    //   "8888888",
    //   "Test Name",
    //   "123",
    //   "segredo"
    // );
    // // userTable.create(manager);
    // userTable.get(manager.getName(), manager.getPassword());

  }
}