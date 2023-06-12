package com.leria.parser.Models.Leria.types;

public class CapacityType {
  private int value;

  public CapacityType(int value) {
    if (value < -1)
      throw new IllegalArgumentException("Capacity must be greater than -1 : (" + value + ")");
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