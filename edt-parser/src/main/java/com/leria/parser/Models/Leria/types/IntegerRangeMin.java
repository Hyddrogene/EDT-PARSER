package com.leria.parser.Models.Leria.types;

import java.util.regex.Pattern;

public class IntegerRangeMin {
  private static final Pattern PATTERN = Pattern.compile("\\d+(-\\d?)?");
  private String value;

  public IntegerRangeMin(String value) {
    if (!PATTERN.matcher(value).matches())
      throw new IllegalArgumentException("Invalid integer range min: " + value);
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String toString() {
    return value;
  }

}
