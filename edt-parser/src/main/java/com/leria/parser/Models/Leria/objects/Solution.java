package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.List;

public class Solution {
  private List<Group> groups;

  public Solution(List<Group> groups) {
    this.groups = groups;
  }

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  public void addGroup(Group group) {
    groups.add(group);
  }

  public Group getGroupById(String id) {
    for (Group group : groups) {
      if (group.getId().getId().equals(id)) {
        return group;
      }
    }
    return null;
  }

  public void exportXML(FileWriter writer) {
    try {
      writer.write("<solution>\n<groups>\n");
      for (Group group : groups) {
        group.exportXML(writer);
      }
      writer.write("</groups>\n</solution>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting part");
      e.printStackTrace();
    }
  }
}
