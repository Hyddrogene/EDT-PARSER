package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

import com.leria.parser.Models.Leria.types.UniqueId;

public class Equipment {
  // Attributes
  private UniqueId id;
  private int count;

  public Equipment(String id, int count) {
    if (count < 0)
      throw new IllegalArgumentException("count must be non-negative");
    this.id = new UniqueId(id);
    this.count = count;
  }

  public UniqueId getId() {
    return id;
  }

  public int getCount() {
    return count;
  }

  public String toString() {
    return "Equipment [id=" + id + ", count=" + count + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<equipment>\n");
      file.write("  <id>" + id + "</id>\n");
      file.write("  <count>" + count + "</count>\n");
      file.write("</equipment>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting equipment");
      e.printStackTrace();
    }
  }
}
