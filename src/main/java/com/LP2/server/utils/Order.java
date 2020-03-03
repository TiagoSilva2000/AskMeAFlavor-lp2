package com.LP2.server.utils;

import com.LP2.database.misc.OrderController;
import com.LP2.server.items.Item;
import java.sql.Timestamp;
import java.time.Instant;

public class Order {
  private Item item;
  private int qnt;
  private Timestamp ordered_at;
  private byte status;
  private int client_id;
  private int id;



  public Order(Item item, int qnt, final int client_id) {
    this.item = item;
    this.qnt = qnt;
    this.ordered_at = Timestamp.from(Instant.now());
    this.status = Constants.getUnOrder();
    this.client_id = client_id;
    id = OrderController.create(this, client_id);
  }

  public int getID () { return this.id; }

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

  public void setStatus(final byte status) { this.status = status; }

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
