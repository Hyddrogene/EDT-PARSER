package com.leria.parser.Models.Leria.types;

import java.util.regex.Pattern;

public class Label {
  private static final Pattern PATTERN = Pattern.compile("[^&]*");
  private String value;

  public Label(String label) {
    if (!PATTERN.matcher(label).matches())
      throw new IllegalArgumentException("Invalid label: " + label);
    this.value = label;
  }

  public String getValue() {
    return value;
  }

  public String toString() {
    return value;
  }
}
