package com.LP2.models.security;

import com.LP2.models.users.User;

public class Auth {
  private static User user;

  public static void setUser(final User usr) { user = usr; }

  public static User getUser() { return user; }

  public static boolean authenticate(final String password) { return false; }

}