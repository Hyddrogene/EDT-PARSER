package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

public class AllowedSlots {
  // Attributes
  private int sessionLength;

  // Elements
  private String dailySlots;
  private String days;
  private String weeks;

  public AllowedSlots(String dailySlots, String days, String weeks, int sessionLength) {
    if (sessionLength < 0)
      throw new IllegalArgumentException("sessionLength must be non-negative");
    this.dailySlots = dailySlots;
    this.days = days;
    this.weeks = weeks;
    this.sessionLength = sessionLength;
  }

  public String getDailySlots() {
    return dailySlots;
  }

  public String getDays() {
    return days;
  }

  public String getWeeks() {
    return weeks;
  }

  public int getSessionLength() {
    return sessionLength;
  }

  public String toString() {
    return "AllowedSlots [dailySlots=" + dailySlots + ", days=" + days + ", weeks=" + weeks + ", sessionLength="
        + sessionLength + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<allowedSlots sessionLength=\"" + sessionLength + "\">\n");
      file.write("  <dailySlots>" + dailySlots + "</dailySlots>\n");
      file.write("  <days>" + days + "</days>\n");
      file.write("  <weeks>" + weeks + "</weeks>\n");
      file.write("</allowedSlots>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting allowedSlots");
      e.printStackTrace();
    }
  }
}
