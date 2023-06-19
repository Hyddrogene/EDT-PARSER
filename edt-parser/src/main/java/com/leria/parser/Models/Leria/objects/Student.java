package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Student {
  private static int count = 1;
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

  public static int nextId() {
    return count++;
  }

  public static int getCount() {
    return count;
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

  public void addCourseRefId(UniqueId courseRefId) {
    if (!coursesRefIds.contains(courseRefId))
      coursesRefIds.add(courseRefId);
  }

  public String toString() {
    return "Student [id=" + id + ", label=" + label + ", coursesRefIds=" + coursesRefIds + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<student id=\"" + id + "\" label=\"" + label + "\">\n");
      if (coursesRefIds.size() > 0) {
        file.write("<courses>\n");
        for (UniqueId courseRefId : coursesRefIds) {
          file.write("<course refId=" + courseRefId + "/>\n");
        }
        file.write("</courses>\n");
      }
      file.write("</student>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting student");
      e.printStackTrace();
    }
  }
}
