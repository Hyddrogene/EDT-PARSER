package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

import com.leria.parser.Models.Leria.types.IntegerRangeMin;
import com.leria.parser.Models.Leria.types.UniqueId;

public class AllowedRooms {
  // Attributes
  private IntegerRangeMin sessionRooms;

  // Elements
  private List<UniqueId> roomRefIds;

  public AllowedRooms(List<UniqueId> roomRefIds, IntegerRangeMin sessionRooms) {
    this.roomRefIds = roomRefIds;
    this.sessionRooms = sessionRooms;
  }

  public List<UniqueId> getRooms() {
    return roomRefIds;
  }

  public IntegerRangeMin getSessionRooms() {
    return sessionRooms;
  }

  public String toString() {
    return "AllowedRooms [roomRefIds=" + roomRefIds + ", sessionRooms=" + sessionRooms + "]";
  }

  public void exportXML(FileWriter file) {
    try {
      file.write("<allowedRooms sessionRooms=\"" + sessionRooms + "\">\n");
      for (UniqueId roomRefId : roomRefIds) {
        file.write("  <room refId=\"" + roomRefId + "\" />\n");
      }
      file.write("</allowedRooms>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting allowedRooms");
      e.printStackTrace();
    }
  }
}
