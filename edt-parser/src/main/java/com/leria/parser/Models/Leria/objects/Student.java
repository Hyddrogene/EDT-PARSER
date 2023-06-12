package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Student {
  // Attributes
  private UniqueId id;
  private Label label;

  // Elements
  private List<UniqueId> coursesRefIds;

  public Student(String id, String label, List<UniqueId> coursesRefIds) {
    this.id = new UniqueId(id);
    this.label = new Label(label);
    this.coursesRefIds = coursesRefIds;
  }

  public UniqueId getId() {
    return id;
  }

  public Label getLabel() {
    return label;
  }

  public List<UniqueId> getCoursesRefIds() {
    return coursesRefIds;
  }

  public String toString() {
    return "Student [id=" + id + ", label=" + label + ", coursesRefIds=" + coursesRefIds + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<student>\n");
      file.write("  <id>" + id + "</id>\n");
      file.write("  <label>" + label + "</label>\n");
      file.write("  <coursesRefIds>\n");
      for (UniqueId courseId : coursesRefIds) {
        file.write("    <courseRefId>" + courseId + "</courseRefId>\n");
      }
      file.write("  </coursesRefIds>\n");
      file.write("</student>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting student");
      e.printStackTrace();
    }
  }
}
