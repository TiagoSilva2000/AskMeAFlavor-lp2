package com.LP2.server.utils;

import com.LP2.server.items.Item;
import java.sql.Timestamp;
import java.time.Instant;

public class Order {
  Item item;
  int qnt;
  Timestamp ordered_at;
  byte status;

  public Order(Item item, int qnt) {
    this.item = item;
    this.qnt = qnt;
    this.ordered_at = Timestamp.from(Instant.now());
    this.status = Constants.getUnOrder();
  }

  public void setItem(Item newItem) {
    this.item = newItem;
  }

  public void setQnt(int newQnt) {
    this.qnt = newQnt;
  }

  public Item getItem() {
    return this.item;
  }

  public int getQnt() {
    return this.qnt;
  }

  public Timestamp getTime() {
    return this.ordered_at;
  }

  public byte getStatus() {
    return this.status;
  }

  public double getCost() {
    return this.qnt * this.item.getPrice();
  }

  String getOrderString() {
    String output = String.format(
      "%s - R$%.2f x %d",
      this.item.getName(),
      this.item.getPrice(),
      this.qnt
    );

    return output;
  }
}
