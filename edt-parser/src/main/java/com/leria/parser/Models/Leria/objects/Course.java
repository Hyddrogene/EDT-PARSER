package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Course {
  // Attributes
  private UniqueId id;
  private Label label;

  // Elements
  private List<Part> parts;

  public Course(String id, String label) {
    this.id = new UniqueId(id);
    this.label = new Label(label);
    this.parts = new ArrayList<>();
  }

  public void setId(UniqueId id) {
    this.id = id;
  }

  public void setLabel(Label label) {
    this.label = label;
  }

  public void setParts(List<Part> parts) {
    this.parts = parts;
  }

  public void addPart(Part part) {
    this.parts.add(part);
  }

  public UniqueId getId() {
    return id;
  }

  public Label getLabel() {
    return label;
  }

  public List<Part> getParts() {
    return parts;
  }

  public String toString() {
    return "Course [id=" + id + ", label=" + label + ", parts=" + parts + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      if (parts.isEmpty()) {
        return;
      }
      file.write("<course id=\"" + id + "\" label=\"" + label + "\">\n");
      if (parts != null && !parts.isEmpty()) {
        for (Part part : parts) {
          part.exportXML(file);
        }
      }
      file.write("</course>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting course");
      e.printStackTrace();
    }
  }
}
