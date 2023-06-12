package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

import com.leria.parser.Models.Leria.types.IntegerRange;
import com.leria.parser.Models.Leria.types.UniqueId;

public class AllowedTeacher {
  // Attributes
  private UniqueId refId;
  private IntegerRange nrSessions;

  public AllowedTeacher(String refId, IntegerRange nrSessions) {
    this.refId = UniqueId.getUniqueId(refId);
    this.nrSessions = nrSessions;
  }

  public UniqueId getRefId() {
    return refId;
  }

  public IntegerRange getNrSessions() {
    return nrSessions;
  }

  public String toString() {
    return "AllowedTeacher [refId=" + refId + ", nrSessions=" + nrSessions + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<allowedTeacher refId=\"" + refId + "\" nrSessions=\"" + nrSessions + "\" />\n");
    } catch (Exception e) {
      System.out.println("Error while exporting allowedTeacher");
      e.printStackTrace();
    }
  }
}
