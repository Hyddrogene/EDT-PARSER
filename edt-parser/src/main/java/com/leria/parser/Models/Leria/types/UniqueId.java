package com.leria.parser.Models.Leria.types;

import java.util.ArrayList;
import java.util.List;

public class UniqueId {
  private static List<UniqueId> ids = new ArrayList<>();
  private String id;

  public UniqueId(String id) {
    if (exists(id))
      throw new IllegalArgumentException("Id already exists (" + id + ")");
    ids.add(this);
    this.id = id;
  }

  public static UniqueId getUniqueId(String id) {
    UniqueId i = find(id);
    if (i == null)
      throw new IllegalArgumentException("The Id : \"" + id + "\" does not exist");
    return i;
  }

  public String getId() {
    return id;
  }

  public static boolean exists(String id) {
    for (UniqueId i : ids) {
      if (i.getId().equals(id))
        return true;
    }
    return false;
  }

  public static UniqueId find(String id) {
    for (UniqueId i : ids) {
      if (i.getId().equals(id))
        return i;
    }
    return null;
  }

  public String toString() {
    return id;
  }
}
