package com.LP2.models.validators;

import java.util.ArrayList;

public class ValidationState {
  private boolean accepted;
  private ArrayList<String> messages;
  private ArrayList<Byte> codes;

  public ValidationState(final boolean accepted, final String message, final byte code) {
    this.accepted = accepted;
    this.messages = new ArrayList<String>();
    this.codes = new ArrayList<Byte>();
    this.messages.add(message);
    this.codes.add(code);
  }

  public ValidationState() {
    this.accepted = false;
    this.codes = new ArrayList<Byte>();
    this.messages = new ArrayList<String>();
  }

  public boolean wasAccepted() { return this.accepted; }

  public ArrayList<String> getMessage() { return this.messages; }

  public void addState(String message, byte code) {
    this.messages.add(message);
    this.codes.add(code);
  }

  public ArrayList<Byte> getCode() { return this.codes; }
}