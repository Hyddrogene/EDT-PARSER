package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

public class Classes {
  // Attributes
  private int maxHeadCount;

  // Elements
  private List<Class> classes;

  public Classes(List<Class> classes, int maxHeadCount) {
    if (maxHeadCount < 0)
      throw new IllegalArgumentException("maxHeadCount must be non-negative");
    this.classes = classes;
    this.maxHeadCount = maxHeadCount;
  }

  public List<Class> getClasses() {
    return classes;
  }

  public void addClass(Class classe) {
    classes.add(classe);
  }

  public int getMaxHeadCount() {
    return maxHeadCount;
  }

  public String toString() {
    return "Classes [classes=" + classes + ", maxHeadCount=" + maxHeadCount + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      if (classes.isEmpty()) {
        return;
      }
      file.write("<classes maxHeadCount=\"" + maxHeadCount + "\">\n");
      for (Class classe : classes) {
        classe.exportXML(file);
      }
      file.write("</classes>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting classes");
      e.printStackTrace();
    }
  }
}
