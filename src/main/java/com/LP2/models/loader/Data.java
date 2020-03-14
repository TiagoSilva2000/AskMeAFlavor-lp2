package com.LP2.models.loader;

public class Data {
  private String key, value;

  public Data(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() { return this.key; }

  public String getValue() { return this.value; }

  public String setKey(String key) {
    this.key = key;

    return key;
  }

  public String setValue(String value) {
    this.value = value;

    return value;
  }

}