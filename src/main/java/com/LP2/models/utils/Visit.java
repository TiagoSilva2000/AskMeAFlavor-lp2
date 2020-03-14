package com.LP2.models.utils;

import com.LP2.database.misc.VisitDAO;

public class Visit {
  private int id;

  public Visit(final int clientID) {
    this.id = VisitDAO.create(clientID);
  }

  public Visit(final int visitID, boolean alreadyExists) {
    this.id = visitID;
  }

  public int getId() { return this.id; }
}