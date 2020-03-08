package com.LP2.server.utils;

import com.LP2.database.misc.VisitController;

public class Visit {
  private int id;

  public Visit(final int clientID) {
    this.id = VisitController.create(clientID);
  }

  public Visit(final int visitID, boolean alreadyExists) {
    this.id = visitID;
  }

  public int getId() { return this.id; }
}