package com.LP2.utils;

import com.LP2.items.Item;
import java.sql.Timestamp;
import java.time.Instant;

public class Order {
  Item item;
  int qnt;
  Timestamp ordered_at;

  public Order(Item item, int qnt) {
    this.item = item;
    this.qnt = qnt;
    this.ordered_at = Timestamp.from(Instant.now());
  }

  void setItem(Item newItem) {
    this.item = newItem;
  }

  void setQnt(int newQnt) {
    this.qnt = newQnt;
  }

  Item getItem() {
    return this.item;
  }

  int getQnt() {
    return this.qnt;
  }

  Timestamp getTime() {
    return this.ordered_at;
  }

  public double getCost() {
    return this.qnt * this.item.getPrice();
  }

  String getOrderString() {
    String output = String.format(
      "%s - R$%f x %d",
      this.item.getName(),
      this.item.getPrice(),
      this.qnt
    );

    return output;
  }
}
