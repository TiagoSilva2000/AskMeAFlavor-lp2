package com.LP2.server.utils;

public class Constants {
  static private byte devCode = 0,
              managerCode = 1,
              cookCode = 2,
              clientCode = 3;

  static public byte getDevCode() { return devCode; }

  static public byte getManagerCode() { return managerCode; }

  static public byte getCookCode() { return cookCode; }

  static public byte getClientCode() { return clientCode; }
}