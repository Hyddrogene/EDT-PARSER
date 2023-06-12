package com.leria.parser.Models.Leria.types;

public class Week {
  private static final int WEEKS_IN_YEAR = 53;
  private int value;

  public Week(int value) {
    if (value < 1 && value > WEEKS_IN_YEAR)
      throw new IllegalArgumentException("Weeks must be between 1 and " + WEEKS_IN_YEAR);
    else
      this.value = value;
  }

  public int getValue() {
    return value;
  }

  public String toString() {
    return Integer.toString(value);
  }
}
