package com.leria.parser.Models.UA;

import java.util.ArrayList;
import java.util.List;

public class Repartition {
  private int nrClassesCM;
  private int nrClassesTD;
  private int nrClassesTP;

  private List<GroupTD> groupsTD;
  private List<GroupTP> groupsTP;

  public Repartition(int nrClassesCM, int nrClassesTD, int nrClassesTP, List<GroupTD> groupsTD,
      List<GroupTP> groupsTP) {
    this.nrClassesCM = nrClassesCM;
    this.nrClassesTD = nrClassesTD;
    this.nrClassesTP = nrClassesTP;
    this.groupsTD = groupsTD;
    this.groupsTP = groupsTP;
  }

  public int getNrClassesCM() {
    return nrClassesCM;
  }

  public int getNrClassesTD() {
    return nrClassesTD;
  }

  public int getNrClassesTP() {
    return nrClassesTP;
  }

  public List<GroupTD> getGroupsTD() {
    return groupsTD;
  }

  public List<GroupTP> getGroupsTP() {
    return groupsTP;
  }

  public GroupTP getGroupTP(int n) {
    for (GroupTP group : groupsTP) {
      if (group.getN() == n)
        return group;
    }
    return null;
  }

  public void setNrClassesCM(int nrClassesCM) {
    this.nrClassesCM = nrClassesCM;
  }

  public void setNrClassesTD(int nrClassesTD) {
    this.nrClassesTD = nrClassesTD;
  }

  public void setNrClassesTP(int nrClassesTP) {
    this.nrClassesTP = nrClassesTP;
  }

  public void setGroupsTD(List<GroupTD> groupsTD) {
    this.groupsTD = groupsTD;
  }

  public void setGroupsTP(List<GroupTP> groupsTP) {
    this.groupsTP = groupsTP;
  }

  public List<GroupTD> getGroupsTDByParent(int parent) {
    return groupsTD.stream().filter(group -> group.getParent() == parent).toList();
  }

  public List<GroupTP> getGroupsTPByParentTD(int parent) {
    return groupsTP.stream().filter(group -> group.getParent() == parent || group.getParent2() == parent).toList();
  }

  public List<GroupTP> getGroupsTPByParentCM(int parent) {
    List<GroupTP> groups = new ArrayList<>();
    getGroupsTDByParent(parent).forEach(group -> groups.addAll(getGroupsTPByParentTD(group.getParent())));
    return groups;
  }

  public String toString() {
    String str = "CM: " + nrClassesCM + " TD: " + nrClassesTD + " TP: " + nrClassesTP;
    str += "\nTD: ";
    for (GroupTD group : groupsTD) {
      str += group.getParent() + " ";
    }
    str += "\nTP: ";
    for (GroupTP group : groupsTP) {
      str += group.toString() + " ";
    }
    return str;
  }
}
