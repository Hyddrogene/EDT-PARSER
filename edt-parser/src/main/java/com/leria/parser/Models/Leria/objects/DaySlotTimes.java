package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.DaySlotTime;

public class DaySlotTimes {
  // Attributes
  private int duration;

  // Elements
  private List<DaySlotTime> daySlotTimes;

  public DaySlotTimes(List<DaySlotTime> daySlotTimes, int duration) {
    if (duration < 0) {
      throw new IllegalArgumentException("Duration must be non-negative");
    }
    this.daySlotTimes = daySlotTimes;
    this.duration = duration;
  }

  public List<DaySlotTime> getdaySlotTimes() {
    return daySlotTimes;
  }

  public int getDuration() {
    return duration;
  }

  public String toString() {
    return "DaySlotTimes [daySlotTimes=" + daySlotTimes + ", duration=" + duration + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<daySlotTimes>\n");
      for (DaySlotTime daySlotTime : daySlotTimes) {
        file.write("  <daySlotTime>" + daySlotTime + "</daySlotTime>\n");
      }
      file.write("</daySlotTimes>\n");
    } catch (Exception e) {
      System.out.println("Error in DaySlotTimes exportXML");
      e.printStackTrace();
    }
  }
}
