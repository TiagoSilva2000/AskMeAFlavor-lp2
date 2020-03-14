package com.LP2.controllers.users;

// import com.LP2.database.users.UserDAO;
import com.LP2.models.security.Auth;
import com.LP2.models.security.Encrypt;
import com.LP2.models.users.User;
import com.LP2.models.utils.Constants;

import java.util.ArrayList;

public class UserController {

  static public User create(final String name, final String email, final String password,
                    final String idCode, final String phone, final byte usertype) {

    // final String lockedPass = Encrypt.
    User user = new User(name, email, password, idCode, phone, usertype);
    if (usertype == Constants.getClientCode())
        Auth.setUser(user);

    return user;
  }

  static public User read(final int id) {
    User user = new User(id);

    return user;
  }

  static public ArrayList<User> all(final byte userType) {
    ArrayList<User> usr = User.all(userType);


    return usr;
  }

  static public Object[][] allObj(final byte userType) {
    ArrayList<User> users = User.all(userType);

    return buildObjMatrix(users);
  }

  static public User update(final int id, final String name, final String email,
  final String cpf, final String phone) {
    Auth.getUser().setEmail(email);
    Auth.getUser().setName(name);
    Auth.getUser().setIDCode(cpf);
    Auth.getUser().setPhone(phone);

    byte result = User.update(Auth.getUser());
    if (result == 0)
      return Auth.getUser();

    return null;
  }

  static public void delete(final int id, final String password) {
    boolean verified = Auth.authenticate(password);

    if (verified)
      User.delete(id);
  }

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

    Object[][] ob = objs.toArray(new Object[0][]);
    return ob;
  }
}