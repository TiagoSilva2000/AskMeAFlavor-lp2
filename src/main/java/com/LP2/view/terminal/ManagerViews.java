package com.LP2.view.terminal;

public class ManagerViews {
  // static private String option;
  static private char charOption;

  static private char lobby(String options, String text) {
    do {
      System.out.println(text);
      charOption = Screen.readOption(options);
    } while (charOption == '\0');

    return charOption;
  }

  static public char renderLobby () {
    String text =
        "1 - Item\n" +
        "2 - Cook\n" +
        "0 - Return";
    String options = "012";

    return lobby(options, text);
  }

  static public char renderItemLobby() {
    String text =
      "1 - Add a new Item\n" +
      "2 - Remove an Item\n" +
      "3 - Update Item's Info\n" +
      "4 - Index Items\n" +
      "5 - Show Item\n" +
      "0 - Return";
    String options = "012345";

    return lobby(options, text);
  }

  static public char renderCookLobby() {
    String text =
      "1 - Hire a New Cook\n" +
      "2 - Dismiss a Cook\n" +
      "3 - Promote a Cook\n" +
      "4 - Check Cook's Info\n" +
      "5 - Index Cooks\n" +
      "0 - Return";
    String options = "01235";

    return lobby(options, text);
  }


}