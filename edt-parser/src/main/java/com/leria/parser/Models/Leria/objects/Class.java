package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Class {
  // Attributes
  private UniqueId id;
  private UniqueId parent;
  private Label label;

  public Class(String id, String parent, String label) {
    this.id = new UniqueId(id);
    this.parent = UniqueId.getUniqueId(parent);
    this.label = new Label(label);
  }

  public Class(String id, String label) {
    this.id = new UniqueId(id);
    this.parent = null;
    this.label = new Label(label);
  }

  public UniqueId getId() {
    return id;
  }

  public UniqueId getParent() {
    return parent;
  }

  public Label getLabel() {
    return label;
  }

  public String toString() {
    return "Class [id=" + id + ", parent=" + parent + ", label=" + label + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<class id=\"" + id + (this.parent != null ? "\" parent=\"" + this.parent : "") + "\" label=\"" + label
          + "\" />\n");
    } catch (Exception e) {
      System.out.println("Error while exporting class");
      e.printStackTrace();
    }
  }
}
