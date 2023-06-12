package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

import com.leria.parser.Models.Leria.types.CapacityType;
import com.leria.parser.Models.Leria.types.Label;
import com.leria.parser.Models.Leria.types.UniqueId;

public class Room {
  private UniqueId id;
  private CapacityType capacity;
  private Label label;
  private String type;

  public Room(String id, int capacity, String label, String type) {
    this.id = new UniqueId(id);
    this.capacity = new CapacityType(capacity);
    this.label = new Label(label);
    this.type = type;
  }

  public UniqueId getId() {
    return id;
  }

  public CapacityType getCapacity() {
    return capacity;
  }

  public Label getLabel() {
    return label;
  }

  public String getType() {
    return type;
  }

  public String toString() {
    return "Room [id=" + id + ", capacity=" + capacity + ", label=" + label + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<room id=\"" + id + "\" capacity=\"" + capacity + "\" label=\"" + label + "\" />\n");
    } catch (Exception e) {
      System.out.println("Error while exporting room");
      e.printStackTrace();
    }
  }
}
