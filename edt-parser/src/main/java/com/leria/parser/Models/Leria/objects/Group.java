package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.UniqueId;

public class Group {
  private UniqueId id;
  private List<String> students;
  private List<String> classes;

  public Group(String id, List<String> students, List<String> classes) {
    this.id = new UniqueId(id);
    this.students = students;
    this.classes = classes;
  }

  public UniqueId getId() {
    return id;
  }

  public void setId(UniqueId id) {
    this.id = id;
  }

  public List<String> getStudents() {
    return students;
  }

  public List<String> getClasses() {
    return classes;
  }

  public void setStudents(List<String> students) {
    this.students = students;
  }

  public void setClasses(List<String> classes) {
    this.classes = classes;
  }

  public void addStudent(String student) {
    students.add(student);
  }

  public void addClass(String classe) {
    if (!classes.contains(classe)) {
      classes.add(classe);
    }
  }

  public void exportXML(FileWriter writer) {
    try {
      if (students.isEmpty() && classes.isEmpty())
        return;
      writer.write("<group id=\"" + id + "\">\n");
      if (!students.isEmpty()) {
        writer.write("<students>\n");
        for (String student : students) {
          writer.write("<student refId=\"" + student + "\"/>\n");
        }

      }
      if (!classes.isEmpty()) {
        writer.write("<classes>\n");
        for (String classe : classes) {
          writer.write("<class refId=\"" + classe + "\"/>\n");
        }
        writer.write("</classes>\n");
      }
      writer.write("</group>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting part");
      e.printStackTrace();
    }
  }
}
