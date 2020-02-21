package com.LP2.app;

import java.util.Scanner;

public class Reader {
  static Scanner scanner;

  static public void loadStream () {
    scanner = new Scanner(System.in);
  }

  static public void unloadStream() {
    scanner.close();
  }

  static public Scanner getScanner() {
    return scanner;
  }
}