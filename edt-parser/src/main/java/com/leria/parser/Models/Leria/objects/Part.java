package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Part {
  // Attributes
  private UniqueId id;
  private int nrSessions;
  private Label label;

  // Elements
  private Classes classes;
  private AllowedSlots allowedSlots;
  private AllowedRooms allowedRooms;
  private AllowedTeachers allowedTeachers;

  public Part(String id, int nrSessions, String label) {
    if (nrSessions < 0)
      throw new IllegalArgumentException("nrSessions must be non-negative");
    this.id = new UniqueId(id);
    this.nrSessions = nrSessions;
    this.label = new Label(label);
  }

  public UniqueId getId() {
    return id;
  }

  public int getNrSessions() {
    return nrSessions;
  }

  public Label getLabel() {
    return label;
  }

  public Classes getClasses() {
    return classes;
  }

  public void setClasses(Classes classes) {
    this.classes = classes;
  }

  public AllowedSlots getAllowedSlots() {
    return allowedSlots;
  }

  public void setAllowedSlots(AllowedSlots allowedSlots) {
    this.allowedSlots = allowedSlots;
  }

  public AllowedRooms getAllowedRooms() {
    return allowedRooms;
  }

  public void setAllowedRooms(AllowedRooms allowedRooms) {
    this.allowedRooms = allowedRooms;
  }

  public AllowedTeachers getAllowedTeachers() {
    return allowedTeachers;
  }

  public void setAllowedTeachers(AllowedTeachers allowedTeachers) {
    this.allowedTeachers = allowedTeachers;
  }

  public String toString() {
    return "Part [id=" + id + ", nrSessions=" + nrSessions + ", label=" + label + ", classes="
        + classes + ", allowedSlots=" + allowedSlots + ", allowedRooms=" + allowedRooms
        + ", allowedTeachers=" + allowedTeachers + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<part id=\"" + id + "\" nrSessions=\"" + nrSessions + "\""
          + " label=\"" + label + "\">\n");
      if (classes != null)
        classes.exportXML(file);
      if (allowedSlots != null)
        allowedSlots.exportXML(file);
      if (allowedRooms != null)
        allowedRooms.exportXML(file);
      if (allowedTeachers != null)
        allowedTeachers.exportXML(file);
      file.write("    </part>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting part");
      e.printStackTrace();
    }
  }
}
