package com.LP2.server.utils;

public class Constants {
  static private byte devCode = 0,
              managerCode = 1,
              cookCode = 2,
              clientCode = 3;
  static private byte canceledOrder = -1,
                      unfinishedOrder = 0,
                      finishedOrder = 1;


  static public byte getDevCode() { return devCode; }

  static public byte getManagerCode() { return managerCode; }

  static public byte getCookCode() { return cookCode; }

  static public byte getClientCode() { return clientCode; }

  static public byte getUnOrder() { return unfinishedOrder; }

  static public byte getCanceledOrder() { return canceledOrder; }

  static public byte getFinishedOrder() { return finishedOrder; }

  static public String getUserString (byte code) {
    switch(code) {
      case 0: return "Developer";
      case 1: return "Manager";
      case 2: return "Cook";
      case 3: return "Client";
      default: return "ERROR 400";
    }
  }

  static public String getOrderString(byte code) {
    switch (code) {
      case -1: return "Canceled";
      case 0: return "Unfinished";
      case 1: return "Finished";
      default: return "ERROR 400";
    }

  }
}