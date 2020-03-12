package com.LP2.controllers;

import java.util.ArrayList;

import com.LP2.database.users.UserController;
import com.LP2.server.users.User;
import com.LP2.server.utils.Constants;

public class CookVV {

  static public void create() {}

  static public void read() {}


  static private Object[][] buildObjMatrix(final ArrayList<User> users) {
    ArrayList<Object[]> objs = new ArrayList<Object[]>();

    for (int i = 0; i < users.size(); i++) {
      objs.add(new Object[]{
        users.get(i).getID(),
        users.get(i).getName(),
        users.get(i).getEmail(),
        users.get(i).getIDCode()
      });
    }
    objs.add(new Object[]{
      null,
      null,
      null,
      null
    });

    Object[][] ob = objs.toArray(new Object[0][]);
    return ob;
  }

  static public ArrayList<User> all() {
    ArrayList<User> cooks = UserController.all(Constants.getCookCode());

    return cooks;
  }

  static public Object[][] allObj() {
    ArrayList<User> cooks = UserController.all(Constants.getCookCode());

    return buildObjMatrix(cooks);
  }

  static public void update() {}

  static public void delete() {}

}