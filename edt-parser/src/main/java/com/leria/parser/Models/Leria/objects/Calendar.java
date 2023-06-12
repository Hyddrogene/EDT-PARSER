package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.Week;
import com.leria.parser.Models.Leria.types.WeekDay;

public class Calendar {
  private int startingYear;
  private List<Week> weeks;
  private List<WeekDay> weekDays;
  private DaySlotTimes daySlotTimes;

  public Calendar(int startingYear, List<Week> weeks, List<WeekDay> weekDays, DaySlotTimes daySlotTimes) {
    this.startingYear = startingYear;
    this.weeks = weeks;
    this.weekDays = weekDays;
    this.daySlotTimes = daySlotTimes;
  }

  public int getStartingYear() {
    return startingYear;
  }

  public List<Week> getWeeks() {
    return weeks;
  }

  public List<WeekDay> getWeekDays() {
    return weekDays;
  }

  public DaySlotTimes getDaySlotTimes() {
    return daySlotTimes;
  }

  public String toString() {
    return "Calendar [startingYear=" + startingYear + ", weeks=" + weeks + ", weekDays=" + weekDays + ", daySlotTimes="
        + daySlotTimes + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<calendar>\n");
      file.write("  <startingYear>" + startingYear + "</startingYear>\n");
      file.write("  <weeks>\n");
      for (Week week : weeks) {
        file.write("    <week>" + week + "</week>\n");
      }
      file.write("  </weeks>\n");
      file.write("  <weekDays>\n");
      for (WeekDay weekDay : weekDays) {
        file.write("    <weekDay>" + weekDay + "</weekDay>\n");
      }
      file.write("  </weekDays>\n");
      daySlotTimes.exportXML(file);
      file.write("</calendar>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting calendar");
    }
  }
}
