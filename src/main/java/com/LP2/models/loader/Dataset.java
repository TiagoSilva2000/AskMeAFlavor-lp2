package com.LP2.models.loader;

import java.util.ArrayList;

public class Dataset {
  static private ArrayList<Data> variables;

  public Dataset() {}

  static private int searchKey (String key) {
    int i = 0;

    if (variables == null) {
      variables = new ArrayList<Data>();
    }

    while (i < variables.size()) {
      if (key.equals(variables.get(i).getKey()))
        return i;
      i++;
    }

    return -1;
  }

  static public boolean addVariable(String key, String value) {
    if (variables == null) {
      variables = new ArrayList<Data>();
    }

    if (searchKey(key) != -1) {
      throw new Error("There is already a key with this name!");
    }
    variables.add(new Data(key, value));

    return true;
  }

  static public boolean addVariable(Data data) {
    if (variables == null) {
      variables = new ArrayList<Data>();
    }

    if (searchKey(data.getKey()) != -1) {
      throw new Error("There is already a key with this name!");
    }
    variables.add(data);

    return true;
  }

  static public String setKey (String oldKey, String newKey) {
    int idx = searchKey(oldKey);

    if (idx == -1)
      throw new Error("The selected key wasn't found!");

    variables.get(idx).setKey(newKey);
    return newKey;
  }

  static public String setValue(String key, String newValue) {
    int idx = searchKey(key);

    if (idx == -1)
      throw new Error("The selected key wasn't found!");

    variables.get(idx).setValue(newValue);
    return newValue;
  }

  static public String getValue(String key) {
    int idx = searchKey(key);

    if (idx == -1)
      throw new Error("The selected key wasn't found!");

    return variables.get(idx).getValue();
  }

  static public ArrayList<Data> getVariables() { return variables; }
}