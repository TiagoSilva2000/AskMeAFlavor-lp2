package com.LP2.models.loader;

import java.util.ArrayList;
import java.util.Map;

public class DataHolder {
  private Map<String, Object> values;

  public DataHolder() {}

  public DataHolder(final ArrayList<Data> dataList) {}

  public void rmv(final String key) {
    this.values.remove(key);
  }

  public void set(final String key, final Object value) {
    this.values.put(key, value);
  }

  public Object get(final String key) { return values.get(key); }

}