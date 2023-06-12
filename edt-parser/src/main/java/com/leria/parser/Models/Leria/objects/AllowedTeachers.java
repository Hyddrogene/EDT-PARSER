package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.IntegerRange;

public class AllowedTeachers {
  // Attributes
  private IntegerRange sessionTeachers;

  // Elements
  private List<AllowedTeacher> allowedTeachers;

  public AllowedTeachers(List<AllowedTeacher> allowedTeachers, IntegerRange sessionTeachers) {
    this.allowedTeachers = allowedTeachers;
    this.sessionTeachers = sessionTeachers;
  }

  public List<AllowedTeacher> getAllowedTeachers() {
    return allowedTeachers;
  }

  public IntegerRange getSessionTeachers() {
    return sessionTeachers;
  }

  public String toString() {
    return "AllowedTeachers [allowedTeachers=" + allowedTeachers + ", sessionTeachers=" + sessionTeachers + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<allowedTeachers sessionTeachers=\"" + sessionTeachers + "\">\n");
      for (AllowedTeacher allowedTeacher : allowedTeachers) {
        allowedTeacher.exportXML(file);
      }
      file.write("</allowedTeachers>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting allowedTeachers");
      e.printStackTrace();
    }
  }
}
