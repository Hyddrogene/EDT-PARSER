package com.leria.parser.Models.UA;

import java.util.ArrayList;
import java.util.List;

public class Etape extends BaseEtape {
  List<UACourse> courses;

  public Etape(String codeEtape, String libelleEtape, List<UACourse> courses) {
    this.codeEtape = codeEtape;
    this.libelleEtape = libelleEtape;
    this.courses = courses;
  }

  public Etape(BaseEtape baseEtape, UACourse[] courses) {
    this.codeEtape = baseEtape.getCodeEtape();
    this.libelleEtape = baseEtape.getLibEtape();
    this.courses = new ArrayList<>();
    for (UACourse course : courses) {
      this.courses.add(course);
    }
  }

  public List<UACourse> getCourses() {
    return courses;
  }

  public void setCourses(List<UACourse> courses) {
    this.courses = courses;
  }

  public String toString() {
    return "Etape [courses=" + courses + ", codeEtape=" + codeEtape + ", libelleEtape=" + libelleEtape + "]";
  }
}
