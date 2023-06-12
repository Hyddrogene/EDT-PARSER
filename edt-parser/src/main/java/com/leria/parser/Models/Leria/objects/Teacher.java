package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Teacher {
  // attributes
  private UniqueId id;
  private Label label;

  public Teacher(String id, String label) {
    this.id = new UniqueId(id);
    this.label = new Label(label);
  }

  public UniqueId getId() {
    return id;
  }

  public Label getLabel() {
    return label;
  }

  public String toString() {
    return "Teacher [id=" + id + ", label=" + label + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<teacher id=\"" + id + "\" label=\"" + label + "\"/>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting teacher");
      e.printStackTrace();
    }
  }
}
