package com.leria.parser.Models.Leria.types;

import java.time.LocalTime;

public class DaySlotTime {
  private LocalTime value;

  public DaySlotTime(LocalTime value) {
    this.value = value;
  }

  public LocalTime getValue() {
    return value;
  }

  public String toString() {
    return value.toString();
  }
}
