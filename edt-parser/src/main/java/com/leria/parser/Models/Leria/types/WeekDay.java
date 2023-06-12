package com.leria.parser.Models.Leria.types;

public class WeekDay {
  private static final int DAYS_IN_WEEK = 7;
  private int value;

  public WeekDay(int value) {
    if (value < 1 && value > DAYS_IN_WEEK)
      throw new IllegalArgumentException("week days must be between 1 and " + DAYS_IN_WEEK);
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
