package com.LP2.models.feedback;

public class Review {
  private int itemID;
  private int clientID;
  private String text;

  public Review(int itemID, int clientID, String text) {
    this.itemID = itemID;
    this.clientID = clientID;
    this.text = text;
  }

  public int getItemID() { return this.itemID; }

  public int getClientID() { return this.clientID; }

  public String getText() { return this.text; }
}